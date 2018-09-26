package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends RationBasePage {

    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[1]/input")
    private WebElement loginInput;

    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[2]/input")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[4]/button")
    private WebElement signButton;

    @FindBy(xpath = "//*[@id='lastNameGroup']/div/span/div/p")
    private WebElement error;

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get("https://ration.io/login");
    }

    public WebElement getLoginInput() {
        return loginInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getSignButton() {
        return signButton;
    }

    public WebElement getError() {
        return error;
    }
}

