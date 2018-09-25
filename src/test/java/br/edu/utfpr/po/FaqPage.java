package br.edu.utfpr.po;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FaqPage extends RationBasePage {
    
    @FindBy(xpath = "//*[@id=\"faq\"]/div/div[1]/h4")
    WebElement firstH4;
    
    public FaqPage(WebDriver driver) {
        super(driver);
    }
    
    public String getFirstH4() {
        return firstH4.getText().trim();
    }
}
