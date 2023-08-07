package com.ti.seleniumpompagespom;

import org.openqa.selenium.By;

public class LoginPage extends MainPage {

    private By txtUsername= By.cssSelector("#username");
    private By txtPw=By.cssSelector("#password");

    private By btnLogin = By.xpath("//button[contains(text(),'Log')]");


    private void typeUsername(String username) {
        driver.findElement(txtUsername).clear();
        driver.findElement(txtUsername).sendKeys(username);
    }

    private void typePassword(String password) {
        driver.findElement(txtPw).clear();
        driver.findElement(txtPw).sendKeys(password);
    }


    private void clickLogin() {
        driver.findElement(btnLogin).click();
    }


    public void login(String username, String password) {
        typeUsername(username);
        typePassword(password);
        clickLogin();
}

}
