package br.edu.utfpr.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThingsPage extends RationBasePage {
    
    @FindBy(xpath = "//*[@id=\"available-things\"]/div/div[1]/h1")
    private WebElement title;
        
    public ThingsPage(WebDriver driver) {
        super(driver);
        
    }
    
    public String getTitle() {
        return title.getText().trim();
    }
    
}
