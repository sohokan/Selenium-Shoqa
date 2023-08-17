package com.ti.seleniumtests;


import com.ti.seleniumpompagespom.LoginPage;
import com.ti.seleniumpompagespom.SearchitemPage;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.ti.base.BrowserType;
import org.ti.base.DriverFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Base {

    protected String baseUrl = "https://shop.demoqa.com/shop/";
    protected String username = "103124";
    protected String password = "gamer$345566";
    Map<String, String> userCredentials = new HashMap<>();
    LoginPage loginPage;

    SearchitemPage searchitemPage;




    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));
        DriverFactory.getInstance().getDriver().navigate().to(baseUrl);
        userCredentials.put("username", username);
        userCredentials.put("password", password);

        loginPage = new LoginPage();
        searchitemPage=new SearchitemPage();




    }





    public void addCookies()

    {

        //seems the add items to chekout cookies partially work the rest of the comments lines are for the login which doenst work
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getInstance().getDriver();

        DriverFactory.getInstance().getDriver().manage().deleteAllCookies();


        ((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("localStorage.clear();");
//        String hash = (String) ((JavascriptExecutor) driver).executeScript("localStorage.getItem(\"wc_cart_hash_2265a9f6fbc383987ee8d0afbe044d95\");");
        Cookie autorizationCookie = new Cookie("wfwaf-authcookie-b4b3ad63482ce2b734f2ea9c6f324b03", "11398%7Cother%7Cread%7Caa4b5d59c14af445ccb90e2605dedbea56a1c6fbcb487524dcca0dd89c2fc120");
        Cookie loginCookie = new Cookie("wordpress_logged_in_4e71f76abb937cf9d7ab883171725a64", "103124%7C1691370585%7C0B2sOjNOBzHNpZmMrQ2EGYtdCmK0vbjmZCKl6aJmrk9%7C9afba0b2697a13526a95f77eb79ae686dc31fbacfb56177cadf0d4cb6b651036");

//        DriverFactory.getInstance().getDriver().manage().addCookie(loginCookie);
//        DriverFactory.getInstance().getDriver().manage().addCookie(autorizationCookie);


//        js.executeScript("window.scrollBy(0,500)");



//        String hash = (String) ((JavascriptExecutor) driver).executeScript("localStorage.setItem('wc_cart_hash_','bb3db4fa1c6bed1414bcdd7d7a0e3ad1')");
//        System.out.println(hash);
        Cookie hashCookie = new Cookie("woocommerce_cart_hash", "a020507be91370f012e1f7737b9dd167");

        Cookie itemCookie = new Cookie("woocommerce_items_in_cart", "2");

        DriverFactory.getInstance().getDriver().manage().addCookie(itemCookie);
        DriverFactory.getInstance().getDriver().manage().addCookie(hashCookie);



    }



}
