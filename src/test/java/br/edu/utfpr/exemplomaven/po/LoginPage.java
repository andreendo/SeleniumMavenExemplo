/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author caiot
 */
public class LoginPage extends BasePage{
    
    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get("https://ration.io/login");
    }
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[1]/input")
    WebElement emailInput;
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[2]/input")
    WebElement passwordInput;
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[4]/button")
    WebElement buttonSignIn;
    
    public LoginPage preencherEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
        
        return this;
    }
    
     public LoginPage preencherPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        
        return this;
    }
    
    public FriendsPage clicarBotaoSignIn() {
        buttonSignIn.click();
        return new FriendsPage(driver);
    }
}
