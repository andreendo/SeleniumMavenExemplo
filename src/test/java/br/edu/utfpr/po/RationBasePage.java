package br.edu.utfpr.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RationBasePage extends BasePage {
       
    private NavBar navBar;
    
    public RationBasePage(WebDriver driver) {
        super(driver);
        navBar = new NavBar(driver);
    }
    
    public NavBar getNavBar() {
        return navBar;
    }   
}
