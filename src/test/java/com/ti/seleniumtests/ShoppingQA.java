package com.ti.seleniumtests;

import org.testng.annotations.Test;

public class ShoppingQA extends Base {


    @Test(priority = 1, enabled = false)
    void loginWithRightCredentials() {


        loginPage.login(userCredentials.get("username"), userCredentials.get("password"));
        addCookies();
    }


    @Test(priority = 2, enabled = false)

    public void ItetmsSearch() {

        searchitemPage.searchItem("shirt");

    }

    @Test(priority = 3, enabled = true)

    public void AllItemsFoundSearch() {



        searchitemPage.identifyItems();


    }


    @Test(priority = 4, enabled = true)

    public void SortLowestPriceSearch() {

        searchitemPage.sortPriceAsceding();


    }


    @Test(priority = 5, enabled = true)

    public void MedianPriceSearch() {
        searchitemPage.MedianNumber();

    }
    @Test(priority = 6, enabled = true)

    public void CheapestItemSearch() {
        searchitemPage.cheapestItem();

    }


    @Test(priority = 6, enabled = true)

    public void MostExpensiveItemSearch() {
        searchitemPage.expensiveItem();

    }

    @Test(priority = 6, enabled = true)

    public void AvgtemSearch() {
        searchitemPage.averagePrice();

    }

    @Test(priority = 6, enabled = true)

    public void FilterColor() {
        searchitemPage.Filter_Color("pink");

    }



    @Test(priority = 7, enabled = true)

    public void SortHighestPriceSearch() {
        searchitemPage.sortPriceDescending();

    }



}
