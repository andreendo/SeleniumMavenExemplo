package br.edu.utfpr.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends RationBasePage {
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[1]/input")
    private WebElement email;
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[2]/input")
    private WebElement password;
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[4]/button")
    private WebElement submit;
        
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public LoginPage setEmail(String input) {
        email.sendKeys(input);
        return this;
    }
    
    public LoginPage setPassword(String input) {
        password.sendKeys(input);
        return this;
    }
    
    public ThingsPage submit() {
        submit.submit();
        return new ThingsPage(driver);
    }
}
