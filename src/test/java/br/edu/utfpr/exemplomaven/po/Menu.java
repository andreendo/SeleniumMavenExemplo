package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Menu extends BasePage {

    @FindBy(tagName = "h1")
    WebElement title;

    @FindBy(xpath = "//*[@id=\"page-header\"]/a")
    WebElement home;
    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[1]")
    WebElement faq;
    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[2]")
    WebElement login;
    @FindBy(xpath = "//*[@id=\"page-header\"]/div/div/a")
    WebElement signUp;

    public Menu(WebDriver driver) {
        super(driver);
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getHome() {
        return home;
    }

    public WebElement getFaq() {
        return faq;
    }

    public WebElement getLogin() {
        return login;
    }

    public WebElement getSignUp() {
        return signUp;
    }

    private void clickMenuOption(WebElement menuOption) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( ExpectedConditions.elementToBeClickable(menuOption));
        menuOption.click();
    }
    public LoginPage goToLogin() {
        clickMenuOption(getLogin());
        return new LoginPage(driver);
    }
    public HomePage goToHome() {
        clickMenuOption(getHome());
        return new HomePage(driver);
    }
    public FaqPage goToFaq() {
        clickMenuOption(getFaq());
        return new FaqPage(driver);
    }
    public SignUpPage goToSignUp() {
        clickMenuOption(getSignUp());
        return new SignUpPage(driver);
    }

}