package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Menu extends BasePage {

    @FindBy(linkText = "Friends")
    WebElement friends;

    @FindBy(linkText = "Things")
    WebElement things;

    @FindBy(linkText = "Help")
    WebElement help;

    @FindBy(linkText = "Account")
    WebElement account;

    @FindBy(linkText = "Settings")
    WebElement settings;

    @FindBy(linkText = "Sign out")
    WebElement signout;

    @FindBy(linkText = "FAQ")
    WebElement faq;

    @FindBy(linkText = "Log in")
    WebElement login;

    @FindBy(linkText = "Sign up")
    WebElement signup;

    public Menu(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLogin() {
        login.click();
        return new LoginPage(driver);
    }

    private void clickMenuOption(WebElement menuOption) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( ExpectedConditions.elementToBeClickable(menuOption) );
        menuOption.click();
    }
}
