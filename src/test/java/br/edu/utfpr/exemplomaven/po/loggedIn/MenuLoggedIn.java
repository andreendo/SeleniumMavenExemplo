package br.edu.utfpr.exemplomaven.po.loggedIn;

import br.edu.utfpr.exemplomaven.po.*;
import br.edu.utfpr.exemplomaven.po.loggedOut.FaqPage;
import br.edu.utfpr.exemplomaven.po.loggedOut.HomePage;
import br.edu.utfpr.exemplomaven.po.loggedOut.LoginPage;
import br.edu.utfpr.exemplomaven.po.loggedOut.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuLoggedIn extends BasePage {

    @FindBy(xpath = "/html/body/div/header/a/img")
    WebElement home;

    @FindBy(xpath = "/html/body/div/header/div/a[1]")
    WebElement friends;

    @FindBy(xpath = "/html/body/div/header/div/a[2]")
    WebElement things;

    @FindBy(xpath = "/html/body/div/header/div/a[3]")
    WebElement help;


    public MenuLoggedIn(WebDriver driver) {
        super(driver);
    }

    public FriendsPage goToFriendsPage() {
        clickMenuOption(friends);
        return new FriendsPage(driver);
    }

    public FriendsPage goToHomePage(){
        clickMenuOption(home);
        return new FriendsPage(driver);
    }

    public ThingsPage goToThingsPage(){
        clickMenuOption(things);
        return new ThingsPage(driver);
    }

    public HelpPage goToHelpPage(){
        clickMenuOption(help);
        return new HelpPage(driver);
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
