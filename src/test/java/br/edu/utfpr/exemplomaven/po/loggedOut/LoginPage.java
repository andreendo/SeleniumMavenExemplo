package br.edu.utfpr.exemplomaven.po.loggedOut;

import br.edu.utfpr.exemplomaven.po.loggedIn.FriendsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends RationLoggedOutBasePage{


    @FindBy(xpath = "/html/body/div/div/div/div/form/div[1]/input")
    WebElement emailInput;

    @FindBy(xpath = "/html/body/div/div/div/div/form/div[2]/input")
    WebElement passwordInput;

    @FindBy(xpath = "/html/body/div/div/div/div/form/div[4]/button")
    WebElement submitButton;

    @FindBy(xpath = "/html/body/div/div/div/div/form/p/small")
    WebElement error;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage setEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage setPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public FriendsPage submitValidCredentials(){
        submitButton.submit();
        waitNextPage("Friends");
        return new FriendsPage(driver);
    }


    public RationLoggedOutBasePage submitInvalidCredentials(){
        submitButton.click();
        return this;
    }

    public String getErrorMessage(){
        return error.getText();
    }


}
