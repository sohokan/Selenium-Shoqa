package com.ti.seleniumpompagespom;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchitemPage extends MainPage {


    private By icon_search = By.cssSelector(".noo-search");
    private By bar_search=By.cssSelector("input[name='s']") ;
    private By filter_Color= By.cssSelector("div[class$='pull-right noo_woocommerce-catalog'] select[name$='filter_color']");
    private By total_products_perpage=By.xpath("//*[@class='noo-product-inner']");

    private By Next_rightarrow_xpath= By.cssSelector(".arrow_carrot-2right");


 /*   (//div[@class='noo-product-inner'])[*]*/
    private By total_pagination= By.xpath("//*[contains(@class,'page-numbers')and contains(text(),*)]");
    private By no_Products_Msg= By.xpath("//p [contains(@class, 'no-products-found')]");



    List<WebElement> Pagination_number;
    WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(7));

    WebElement pagenumbericon;

    WebElement nextRighicon;

    String pageNumberxpath1="//*[contains(@class,'page-numbers')and contains(text(),";
    String pageNumberxpath2=")]";

    int Pagesize;

    int CurrentPageselected;

    public void searchItem( String searchclothname) {


        System.out.println(driver.getCurrentUrl());
        WebElement searchIcon = driver.findElement(icon_search);
         searchIcon.click();
        WebElement search = driver.findElement(bar_search);
        search.sendKeys(searchclothname);
        search.sendKeys(Keys.ENTER);

    }


    public void identifyClothes()
    {


        //WebDriverWait wait2 = new WebDriverWait(driver, 5);

        //	List<WebElement> xpath = (List<WebElement>) By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[1]/div[1]/*");

        //System.out.println(wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[1]/div[1]/*"))).getText());

        //wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"noo-site\"]")));
        //wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[1]/div[1]/*")));
        wait2.until(ExpectedConditions.elementToBeClickable(filter_Color));




        //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        Pagination_number=driver.findElements(total_pagination);
        Pagesize =Pagination_number.size();

//        nextRighicon= driver.findElement(Next_rightarrow_xpath);

        //Boolean doenstexit = wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/div[2]/p"))).size()>0;

        //int items = wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[1]/div"))).size();
        Boolean isPresentcloths =(driver.findElements(total_products_perpage)).size()!=0;

        //System.out.println(driver.findElements(total_products_perpage).size());


        //Boolean isPresentcloths =	isElementPresent(wait2, "/html/body/div[2]/div[2]/div[1]/div/p", driver);

        //System.out.println(isPresentcloths);


        if (isPresentcloths)
        //((wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(total_products_perpage)).size())>0)
        {

            WebElement clothname;
            WebElement clothtype;
            WebElement clothprice;

            String clothnamestr1 = "div[class='products noo-row'] div:nth-child(";

            String clothnamestr2 = ") div:nth-child(1) h3:nth-child(1)";

            String clothtypestr1 = "(//span[contains(@class,'posted_in')])[";
            String clothtypestr2 = "]";

            String clothprice1 = "(//span[contains(@class,'price')])[";
            String clothprice2 = "]";
            System.out.println("THERES CLOTHES");

            //clotheList = new ArrayList<>();


//            System.out.println(driver.findElement(total_products_perpage).getText());
            int item_total;


            // System.out.println(
            // (driver.findElements(By.xpath("/html/body/div[1]/div[2]/p")).getText()));
            int num_pages = 1;
            int totalitems = 0;
            System.out.println("Total pages:" + Pagesize);

            CurrentPageselected = 1;
            Boolean ExistRightarrow;
            do {
                System.out.println("Current :" + num_pages);


                item_total = driver.findElements(total_products_perpage).size();
                System.out.println("Total items :" + item_total);

                ExistRightarrow = (driver.findElements(Next_rightarrow_xpath)).size() != 0;

                int item_num;
                for (item_num = 1; item_num <= item_total; item_num++) {


                    String stringclothname = new StringBuilder(clothnamestr1).append(item_num).append(clothnamestr2)
                            .toString();
//                System.out.println(stringclothname);

                    String stringclothtype = new StringBuilder(clothtypestr1).append(item_num).append(clothtypestr2)
                            .toString();
//                System.out.println(stringclothtype);

                    String clothimg = new StringBuilder("/div[contains(@class,'noo-product-thumbnail')])[").append(item_num)
                            .append("]").toString();

                    String clothicon = new StringBuilder("/html/body/div[2]/div[2]/div[2]/div/div/div[").append(item_num)
                            .append("]/div/div[1]/div[1]/span").toString();

                    String stringclothprice = new StringBuilder(clothprice1).append(item_num).append(clothprice2)
                            .toString();
//               System.out.println(stringclothprice);

                    String stringclothpricerng = new StringBuilder(clothprice1).append(item_num).append("]/div/span[3]")
                            .toString();

                    String stringclothpricedisc = new StringBuilder(clothprice1).append(item_num)
                            .append("]/div/span[3]/ins/span").toString();

                    // WebElement clothname =
                    // wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/h3[1]/a[1]")));
                    clothname = wait2
                            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(stringclothname)));
                    clothtype = wait2
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(stringclothtype)));
                    clothprice = wait2
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(stringclothprice)));


                    System.out.println(clothname.getText());
                    System.out.println(clothtype.getText());
                    System.out.println(clothprice.getText());


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







              /*  String pagenumbericon=new StringBuilder("//a[contains(text(),'").append(page_number)
                        .append("')]").toString();

                System.out.println("page number icon: "+pagenumbericon);*/
/*
                if ((driver.findElements(By.xpath(stringclothprice)).size()) != 0)

                {
                    System.out.println("normalprice");
                    WebElement clothprice = wait2
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(stringclothprice)));

                    System.out.println(clothprice.getText());
                    // clotheList.add(new clothesList(stringclothpricedisc, stringclothpricedisc,
                    // stringclothpricedisc, stringclothpricedisc, stringclothpricedisc));

                    clotheList.add(new clothesList(clothname.getText(), clothtype.getText(), clothprice.getText(),
                            clothimg, clothicon,pagenumbericon));

                } else if ((driver.findElements(By.xpath(stringclothpricedisc)).size()) != 0) {
                    //System.out.println(stringclothpricedisc);
                    System.out.println("discountprice");
                    WebElement clothprice = wait2
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(stringclothpricedisc)));

                    clotheList.add(new clothesList(clothname.getText(), clothtype.getText(), clothprice.getText(),
                            clothimg, clothicon,pagenumbericon));

                } else if ((driver.findElements(By.xpath(stringclothpricerng)).size()) != 0) {
                    System.out.println(stringclothpricerng);
                    System.out.println("pricerange");
                    WebElement clothprice = wait2
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(stringclothpricerng)));
                    System.out.println(clothprice.getText());

                    clotheList.add(new clothesList(clothname.getText(), clothtype.getText(), clothprice.getText(),
                            clothimg, clothicon,pagenumbericon));


                }


                else {
                    // do nothing
                }*/

                    totalitems ++;
                }
                System.out.println("Num pages :" + num_pages);


            } while (ExistRightarrow);
//            while (num_pages <= Pagesize );
            System.out.println("Total items:" + totalitems);
        }
        else {
            System.out.println("THERES NO CLOTHES");

            System.out.println(  wait2.until(ExpectedConditions.presenceOfElementLocated(no_Products_Msg)).getText());
            //System.out.println(driver.findElements(By.xpath("/html/body/div[1]/div[2]/p")).getText());
            System.out.println("exit");

            System.exit(-1);
            driver.close();


        }
    }




    public void sort(List<clothesList> clotheList)

    {
        System.out.println("sorting price");
        Collections.sort(clotheList, Comparator.comparing(clothesList::getclothe_price));
        //System.out.println(clotheList.size());


		/* for (clothesList clothes : clotheList)
		 {
			// System.out.println( clothes.getclothe_name());
		  System.out.println(clothes.clothe_name);
		  System.out.println(clothes.clothe_type);
		  System.out.println(clothes.clothe_price);
		  }
		 */
    }

    public void cheapestclothe(List<clothesList> clotheList)
    {
        WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(10));

        System.out.println("cheap clothes");
        System.out.println(clotheList.get(0).clothe_price);
        System.out.println(clotheList.get(0).clothe_name);
        System.out.println(clotheList.get(0).clothe_img);
        String clothe_page_location=clotheList.get(0).page_number_icon;
        System.out.println("clothes_pagination_location="+clothe_page_location);
        int paginationsize=Pagination_number.size();
        System.out.println("paginationsize: "+paginationsize);
        if (paginationsize>0)
        {
            int clothesnumbericon = driver.findElements(By.xpath(clothe_page_location)).size();
            System.out.println("clothesnumbericon size: "+clothesnumbericon);
            WebElement clothpaginationlocation = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(clothe_page_location)));
            clothpaginationlocation.click();
            WebElement clothimg = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(clotheList.get(0).clothe_img)));
            clothimg.click();
        }
        else
        {
            WebElement clothimg = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(clotheList.get(0).clothe_img)));
            clothimg.click();
        }
    }

    public void expensiveclothe(List<clothesList> clotheList)
    {
		/*WebDriverWait wait2 = new WebDriverWait(driver, 20);
		System.out.println("expensive clothes");
		System.out.println("clothes list size: "+(clotheList.size()));
		System.out.println(clotheList.get(clotheList.size() - 1).clothe_price);
		System.out.println(clotheList.get(clotheList.size() - 1).clothe_name);
		System.out.println(clotheList.get(clotheList.size() - 1).clothe_img);
		WebElement icon = wait2.until(
				ExpectedConditions.elementToBeClickable(By.xpath(clotheList.get(clotheList.size() - 1).clothe_img)));

		icon.click();*/
        WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(10));
        System.out.println("expensive clothes");
        System.out.println(clotheList.get(clotheList.size()-1).clothe_price);
        System.out.println(clotheList.get(clotheList.size()-1 ).clothe_name);
        System.out.println(clotheList.get(clotheList.size()-1).clothe_img);
        String clothe_page_location=clotheList.get(clotheList.size()).page_number_icon;
        System.out.println("clothes_pagination_location="+clothe_page_location);
        int paginationsize=driver.findElements(By.xpath("/html/body/div[2]/div[2]/div[2]/div/nav/ul/*")).size();
        System.out.println("paginationsize: "+paginationsize);
        if (paginationsize >0)
        {
            int clothesnumbericon = driver.findElements(By.xpath(clothe_page_location)).size();
            System.out.println("clothesnumbericon size: "+clothesnumbericon);
            WebElement clothpaginationlocation = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(clothe_page_location)));
            clothpaginationlocation.click();
            WebElement clothimg = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(clotheList.get(clotheList.size()-1 ).clothe_img)));
            clothimg.click();
        }
        else
        {
            WebElement clothimg = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(clotheList.get(clotheList.size()-1).clothe_img)));
            clothimg.click();
        }


    }


    public void paginationbynext() {





        Boolean isPresenttotal_pagination=Pagesize!=0;

        System.out.println("Theres pagination? "+isPresenttotal_pagination);

        wait2 = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.presenceOfElementLocated(total_products_perpage));


        //wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div[2]/div[2]/div/nav/ul")));
        int paginationsize=Pagination_number.size();
        // int paginationsize=driver.findElements(By.xpath("//ul[@class='page-numbers']")).size();
        System.out.println(paginationsize);

        if (Pagesize==0)
        {

            identifyClothes();


        }


        //check pagination to click on page 2 number
        else if (isPresenttotal_pagination)
        {
            //*[contains(@class,'page-numbers')and contains(text(),*)]

            for (int num_pages= 2; num_pages<=(Pagination_number.size()-1); num_pages++) {

                String pageNumberxpath = new StringBuilder(pageNumberxpath1).append(num_pages).append(pageNumberxpath2)
                        .toString();
                pagenumbericon= driver.findElement(By.xpath(pageNumberxpath));

                pagenumbericon.click();

            }




        }




    }

    public boolean isElementPresent( String id) {
        try {

            wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));
            driver.findElement(By.xpath(id));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}

