package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends RationBasePage {

    @FindBy(css = ".ajax-form .form-group:first-child input")
    WebElement inputEmail;

    @FindBy(css = ".ajax-form .form-group:nth-child(2) input")
    WebElement inputSenha;

    @FindBy(css = ".ajax-form .form-group:last-child")
    WebElement btnLogin;

    public LoginPage fillEmail(String text) {
        inputEmail.clear();
        inputEmail.click();
        inputEmail.sendKeys(text);
        return this;
    }

    public LoginPage fillPass(String text) {
        inputSenha.clear();
        inputSenha.click();
        inputSenha.sendKeys(text);
        return this;
    }

    public LoggedPage clickLoginButton() {
        btnLogin.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("friends"));
        return new LoggedPage(driver);
    }


    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
