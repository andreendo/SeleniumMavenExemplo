package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

/**
 *
 * @author CINTIA
 */
public class Menu extends BasePage {

    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[1]")
    WebElement faq;

    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[2]")
    WebElement login;

    @FindBy(xpath = "//*[@id=\"page-header\"]/div/div/a")
    WebElement cadastrar;

    public Menu(WebDriver driver) {
        super(driver);
    }

    private void clickMenuOption(WebElement menuOption) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(menuOption));
        menuOption.click();
    }

    public LoginPage IrParaPaginaLogin() {
        clickMenuOption(login);
        return new LoginPage(driver);
    }
    
    
}
