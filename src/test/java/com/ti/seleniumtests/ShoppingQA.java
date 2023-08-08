package com.ti.seleniumtests;

import org.testng.annotations.Test;

public class ShoppingQA extends Base{


    @Test (priority = 1,enabled=true)
    void loginWithRightCredentials() {


        loginPage.login(userCredentials.get("username"), userCredentials.get("password"));
        addCookies();
    }


    @Test (priority = 2,enabled=true)

    public void searchCloth()
    {

        searchitemPage.searchItem("shirt");

    }

    @Test (priority = 3,enabled=true)

    public void AllClothesFound()
    {

        searchitemPage.identifyClothes();


    }


    @Test (priority = 4,enabled=true)

    public void SortbyPrice()
    {

        searchitemPage.sortPrice();


    }






}
