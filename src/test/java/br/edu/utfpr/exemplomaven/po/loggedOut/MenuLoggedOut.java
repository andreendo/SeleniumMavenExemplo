package br.edu.utfpr.exemplomaven.po.loggedOut;

import br.edu.utfpr.exemplomaven.po.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuLoggedOut extends BasePage {

    @FindBy(xpath = "/html/body/div/header/a/img")
    WebElement home;

    @FindBy(xpath = "/html/body/div/header/div/a[1]")
    private WebElement faq;

    @FindBy(xpath = "/html/body/div/header/div/a[2]")
    WebElement login;

    @FindBy(xpath = "/html/body/div/header/div/div/a")
    WebElement signup;


    public MenuLoggedOut(WebDriver driver) {
        super(driver);
    }

    public FaqPage goToFaqPage() {
        clickMenuOption(faq);
        return new FaqPage(driver);
    }

    public HomePage goToHomePage(){
        clickMenuOption(home);
        return new HomePage(driver);
    }

    public LoginPage goToLoginPage(){
        clickMenuOption(login);
        return new LoginPage(driver);
    }

    public SignUpPage goToSignUpPage(){
        clickMenuOption(signup);
        return new SignUpPage(driver);
    }

    private void clickMenuOption(WebElement menuOption) {
        //menu hides the options -- responsive page
//        if(! menuOption.isDisplayed()) {
//            menuButton.click();
//        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( ExpectedConditions.elementToBeClickable(menuOption) );
        menuOption.click();
    }
}
