package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author CINTIA
 */
public class FriendsPage extends RationPage{
    
    @FindBy(xpath = "//*[@id=\"friends\"]/div/div[1]/h1")
    WebElement title;
    
    public FriendsPage(WebDriver driver) {
        super(driver);
    }
    
    public String VerificaTitulo(){
        return title.getText();
    }
    
}
