package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author CINTIA
 */
public class LoginPage extends RationPage {

    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[1]/input")
    WebElement email;

    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[2]/input")
    WebElement password;

    WebElement remember;

    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[4]/button")
    WebElement btLogar;

    @FindBy(xpath = "//*[@id=\"login\"]/div/div/p/a")
    WebElement esqueceu_senha;
    
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/p/small")
    WebElement error;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public RationPage realizarLogin(String tEmail, String tPass) {
        email.sendKeys(tEmail);
        password.sendKeys(tPass);
        remember.click();
        btLogar.submit();
        return new LoginPage(driver);
    }
    
    public FriendsPage IrParaFriendsPage(){
        return new FriendsPage(driver);
    }
    
    public boolean Error(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(error));
        return error.isDisplayed();
    }
}
