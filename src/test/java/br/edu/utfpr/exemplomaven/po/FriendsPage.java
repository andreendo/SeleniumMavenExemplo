/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author caiot
 */
public class FriendsPage extends BasePage{
    @FindBy(xpath = "//*[@id=\"friends\"]/div/div[1]/div/button")
    WebElement botaoInviteFriends;
    
    public FriendsPage(WebDriver driver) {
        super(driver);
    }
    
    public String getBotaoInvite(){
        return botaoInviteFriends.getText();
    }
    
    public FriendsPage esperarCarregar(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().equals("https://ration.io/friends"));
        
        return this;
    }
    
}
