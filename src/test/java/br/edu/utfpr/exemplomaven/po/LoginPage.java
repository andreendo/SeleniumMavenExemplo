package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[1]/input")
    WebElement inputEmail;
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[2]/input")
    WebElement inputPassword;
    
    @FindBy(className = "ajax-button")
    WebElement loginButton;
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/p/small")
    WebElement errorMsg;
   
    public LoginPage(WebDriver driver) {
        super(driver);
    }   
    
    public LoginPage setEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
        return this;
    }
    
    public LoginPage setPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return this;
    }
    
    public LoginPage submit() {
        loginButton.click();
        return this;
    }
    
    public String getErrorMsg() {
        return errorMsg.getText().trim();
    }
    
}
