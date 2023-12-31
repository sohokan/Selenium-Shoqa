package com.ti.seleniumpompagespom;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class SearchitemPage extends MainPage {


    private By icon_search = By.cssSelector(".noo-search");
    private By bar_search = By.cssSelector("input[name='s']");
    private By filter_Color = By.cssSelector("div[class$='pull-right noo_woocommerce-catalog'] select[name$='filter_color']");
    private By total_products_perpage = By.xpath("//*[@class='noo-product-inner']");

    private By Next_rightarrow_xpath = By.cssSelector(".arrow_carrot-2right");



    private By total_pagination = By.xpath("//*[contains(@class,'page-numbers')and contains(text(),*)]");
    private By no_Products_Msg = By.xpath("//p [contains(@class, 'no-products-found')]");


    List<WebElement> Pagination_number;
    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(7));

    WebElement pagenumbericon;

    WebElement nextRighicon;

    String pageNumberxpath1 = "//*[contains(@class,'page-numbers')and contains(text(),";
    String pageNumberxpath2 = ")]";

    int Pagesize;

    int CurrentPageselected;

    List<ShoppingItems> ItemsList = new ArrayList<>();
    List<Float> RegularClothPrice;


    public void searchItem(String searchclothname) {


        System.out.println(driver.getCurrentUrl());
        WebElement searchIcon = driver.findElement(icon_search);
        searchIcon.click();
        WebElement search = driver.findElement(bar_search);
        search.sendKeys(searchclothname);
        search.sendKeys(Keys.ENTER);

    }


    public void identifyItems() {


        wait2.until(ExpectedConditions.elementToBeClickable(filter_Color));


        Pagination_number = driver.findElements(total_pagination);
        Pagesize = Pagination_number.size();

//


        Boolean isPresentcloths = (driver.findElements(total_products_perpage)).size() != 0;


        if (isPresentcloths)

        {

            WebElement clothname;
            WebElement clothtype;
            WebElement clothprice = null;
            WebElement discountclothprice;
            WebElement clothpricerng;

            String clothnamestr1 = "div[class='products noo-row'] div:nth-child(";

            String clothnamestr2 = ") div:nth-child(1) h3:nth-child(1)";

            String clothtypestr1 = "(//span[contains(@class,'posted_in')])[";
            String clothtypestr2 = "]";

            String clothprice1 = "(//span[contains(@class,'price')])[";
            String clothprice2 = "]";
            System.out.println("THERES CLOTHES");

            int item_total;
            float OldClothPrice;
            float NewClothPrice;
            float HighestClothPrice;
            float LowestClothPrice;
            float Normalprice;



            int num_pages = 1;
            int totalitems = 0;
            System.out.println("Total pages:" + Pagesize);

            CurrentPageselected = 1;
            Boolean ExistRightarrow;
            do {
                System.out.println("Current Page :" + num_pages);


                item_total = driver.findElements(total_products_perpage).size();
                System.out.println("Total items :" + item_total);

                ExistRightarrow = (driver.findElements(Next_rightarrow_xpath)).size() != 0;

                int item_num;
                for (item_num = 1; item_num <= item_total; item_num++) {

                    System.out.printf("Item :%d \n",item_num);


                    String stringclothname = new StringBuilder(clothnamestr1).append(item_num).append(clothnamestr2)
                            .toString();


                    String stringclothtype = new StringBuilder(clothtypestr1).append(item_num).append(clothtypestr2)
                            .toString();


                    String clothimg = new StringBuilder("/div[contains(@class,'noo-product-thumbnail')])[").append(item_num)
                            .append("]").toString();

                    String clothicon = new StringBuilder("/html/body/div[2]/div[2]/div[2]/div/div/div[").append(item_num)
                            .append("]/div/div[1]/div[1]/span").toString();

                    String stringclothprice = new StringBuilder(clothprice1).append(item_num).append(clothprice2)
                            .toString();

                    String normalprice =
                            new StringBuilder("(//div[contains(@class,'noo-product-inner')])[").append(item_num).append("]//child::span[contains(@class,'amount')]")
                                    .toString();


                    String stringclothpricerng = new StringBuilder("(//div[contains(@class,'noo-product-inner')])[").append(item_num).append("]//child::span[contains(text(),'–')]")
                            .toString();


                    String highestclothxpath = new StringBuilder("(//div[contains(@class,'noo-product-inner')])[").append(item_num)
                            .append("]//child:: del[contains(@aria-hidden,'true')]").toString();


                    clothname = wait2
                            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(stringclothname)));
                    clothtype = wait2
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(stringclothtype)));

                    clothprice =
                            wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath(normalprice)));


                    if ((driver.findElements(By.xpath(highestclothxpath)).size()) != 0) {

                        ArrayList<String> setNewPrice = new ArrayList<>();
//                        System.out.println(highestclothxpath);

                        String newPricexpath = new StringBuilder("(//div[contains(@class,'noo-product-inner')])[").append(item_num)
                                .append("]//child:: span[contains(@class,'woocommerce-Price-amount amount')]").toString();


                        List<WebElement> twoprices = driver.findElements(By.xpath(newPricexpath));
                        twoprices.forEach(s -> setNewPrice.add(s.getText()));
                        System.out.println("New Price: " + setNewPrice.get(setNewPrice.size() - 1).replace("₹", ""));
                        OldClothPrice = Float.parseFloat(setNewPrice.get(0).replace("₹", ""));
                        NewClothPrice = Float.parseFloat(setNewPrice.get(setNewPrice.size() - 1).replace("₹", ""));




                        ItemsList.add(new ShoppingItems(clothname.getText(), clothtype.getText(), NewClothPrice, OldClothPrice, stringclothname, num_pages));


                    } else if ((driver.findElements(By.xpath(stringclothpricerng)).size()) != 0) {

//                        System.out.println(stringclothpricerng);

                        System.out.println("pricerange" + driver.findElement(By.xpath(stringclothpricerng)).getText());

                        String[] Pricesplit = driver.findElement(By.xpath(stringclothpricerng)).getText().split(" – ");

                        System.out.println("Hightest price: " + Pricesplit[0].replace("₹", ""));
                        System.out.println("Lowest price :" + Pricesplit[1].replace("₹", ""));


                        HighestClothPrice = Float.parseFloat(Pricesplit[0].replace("₹", ""));
                        LowestClothPrice = Float.parseFloat(Pricesplit[0].replace("₹", ""));

                        ItemsList.add(new ShoppingItems(clothname.getText(), clothtype.getText(), HighestClothPrice, LowestClothPrice, stringclothname, num_pages));
//


                    } else {

//                         clothprice= driver.findElement(By.xpath(highestclothxpath));
                        System.out.println("normal price " + clothprice.getText().replace("₹", ""));


                        // clotheList.add(new ShoppingItems(highestclothxpath, highestclothxpath,
                        // highestclothxpath, highestclothxpath, highestclothxpath));
                        Normalprice = Float.parseFloat(clothprice.getText().replace("₹", ""));

                        ItemsList.add(new ShoppingItems(clothname.getText(), clothtype.getText(), Normalprice, 0, stringclothname, num_pages));

                    }


                    if (item_num == 20) {
                        WebElement nextRighicon = wait2
                                .until(ExpectedConditions.presenceOfElementLocated(Next_rightarrow_xpath));
                        System.out.println("right arrow is displayed " + nextRighicon.isDisplayed());
                        num_pages++;
                        String pageNumberxpath = new StringBuilder(pageNumberxpath1).append(num_pages).append(pageNumberxpath2)
                                .toString();


                        pagenumbericon = wait2
                                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(pageNumberxpath)));


                        CurrentPageselected = Integer.parseInt(pagenumbericon.getText());
                        System.out.println("pagenumbericon:" + CurrentPageselected);
                        pagenumbericon.click();


                    }


                    totalitems++;
                }
                System.out.println("Num pages :" + num_pages);



            } while (ExistRightarrow);
//            while (num_pages <= Pagesize );
            System.out.println("Total items:" + totalitems);

            System.out.println("ArrayList size " + ItemsList.size());

        } else {
            System.out.println("THERES NO CLOTHES");

            System.out.println(wait2.until(ExpectedConditions.presenceOfElementLocated(no_Products_Msg)).getText());
            System.out.println("exit");

            System.exit(-1);



        }
    }


    public void sortPriceAsceding() {
        ItemsList.sort(Comparator.comparing(a -> a.getClothe_price()));


        System.out.println("Sorted Prices lowest to highest");

        for (ShoppingItems clothes : ItemsList) {

            System.out.println("Name: " + clothes.clothe_name);
            System.out.println("Type: " + clothes.clothe_type);
            System.out.println("Normal Price: " + clothes.clothe_price);

        }


        RegularClothPrice = ItemsList.stream()
                .map(b -> b.getClothe_price())
                .collect(Collectors.toList());

    }


    public void sortPriceDescending () {



        ItemsList.sort(Comparator.comparing(ShoppingItems::getClothe_price)
                .reversed());


        System.out.println("Sorted Prices highest to lowest");

        for (ShoppingItems clothes : ItemsList) {
            // System.out.println( clothes.getclothe_name());
            System.out.println("Name: " + clothes.clothe_name);
            System.out.println("Type: " + clothes.clothe_type);
            System.out.println("Normal Price: " + clothes.clothe_price);

        }


    }
        public void MedianNumber() {

        int len = ItemsList.size();

        float median;

        if (RegularClothPrice.size() % 2 == 0) {
            median = RegularClothPrice.get((RegularClothPrice.size() - 1) / 2);
        } else {
            median = (RegularClothPrice.get((RegularClothPrice.size() - 1) / 2) + RegularClothPrice.get(RegularClothPrice.size() / 2)) / 2;
        }


        System.out.println("Median: "+median);

        Integer firstIndex = RegularClothPrice.stream().filter(v -> v.equals(median)).map(v -> RegularClothPrice.indexOf(v)).findFirst()
                .orElse(-1);






        System.out.println("Index:"+firstIndex.intValue());

        System.out.println("Item name: " + ItemsList.get(firstIndex.intValue()).clothe_name + "\nItem Price: " + ItemsList.get(firstIndex.intValue()).clothe_price);

        clickCloth(ItemsList.get(firstIndex.intValue()).clothe_icon,ItemsList.get(firstIndex.intValue()).page_number_icon);

    }

    public void averagePrice()

    {

        double avgPrice = ItemsList.stream().mapToDouble(i -> i.clothe_price).average().getAsDouble();


        System.out.println("Average Price: "+avgPrice);
          // search for the closet average price
        Integer indexAvg=StreamSupport.stream(RegularClothPrice.spliterator(), false)
                .sorted(Comparator.comparingDouble(i -> Math.abs(i - avgPrice))).map(i -> RegularClothPrice.indexOf(i)).findFirst()
                .orElse(-1);


        System.out.println("Index:"+indexAvg.intValue());





        System.out.println("\nItem with the closet average price\nItem name: " + ItemsList.get(indexAvg.intValue()).clothe_name + "\nItem Price: " + ItemsList.get(indexAvg.intValue()).clothe_price);

    }

    public void cheapestItem() {


        System.out.printf("The %s is %.2f \nMakes it the cheapest cloth for this search", ItemsList.get(0).clothe_name, ItemsList.get(0).clothe_price);

//
    }

    public void expensiveItem() {

        System.out.printf("The %s is %.2f \n Makes it the Most expenisve cloth for this search", ItemsList.get(ItemsList.size() - 1).clothe_name, ItemsList.get(ItemsList.size() - 1).clothe_price);



    }

    public void Filter_Color(String color)
    {




        ItemsList.stream()
                .filter(p -> p.clothe_name.contains(color.toUpperCase()))


                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) System.err.println("No results!!");
                    return result;
                })).
                forEach(e ->
                        System.out.println(e.clothe_name + " cost: " + e.clothe_price))

        ;







    }





    public boolean isElementPresent(String id) {
        try {

            wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));
            driver.findElement(By.xpath(id));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void clickCloth(String ClothCss,int PageLocation)
    {

        Boolean isPresenttotal_pagination = Pagesize != 0;

        System.out.println("Theres pagination? " + isPresenttotal_pagination);

        wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.presenceOfElementLocated(total_products_perpage));

        //check pagination to click on page the corresponding page or else just select the item
        if (isPresenttotal_pagination) {

            String pageNumberxpath = new StringBuilder(pageNumberxpath1).append(String.valueOf(PageLocation)).append(pageNumberxpath2) .toString();;
//
            pagenumbericon = driver.findElement(By.xpath(pageNumberxpath));

            pagenumbericon.click();

            driver.findElement(By.cssSelector(ClothCss)).click();

        }

        else {



            driver.findElement(By.cssSelector(ClothCss)).click();

        }



    }


}



