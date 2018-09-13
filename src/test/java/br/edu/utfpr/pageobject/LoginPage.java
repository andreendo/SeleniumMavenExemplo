package br.edu.utfpr.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends InitialPage {

    WebElement email;
    WebElement password;
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getEmail() {
        return email.getAttribute("value");
    }

    public LoginPage setEmail(String pemail) {
        email.clear();
        email.sendKeys(pemail);
        return this;
    }

    public String getPassword() {
        return password.getAttribute("value");
    }

    public LoginPage setPassword(String ppassword) {
        password.clear();
        password.sendKeys(ppassword);
        return this;
    }

    public FriendsPage signInButton() {
        signInButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h1")).
                getText().toLowerCase().startsWith("friends"));

        return new FriendsPage(driver);
    }

}
