package br.edu.utfpr.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavBar extends BasePage {
    
    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[1]")
    private WebElement faq;
    
    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[2]")
    private WebElement login;
    
    @FindBy(xpath = "//*[@id=\"page-header\"]/div/div/a")
    private WebElement signUp;
    
    @FindBy(xpath = "//*[@id=\"page-header\"]/a/img")
    private WebElement home;
 
    public NavBar(WebDriver driver) {
        super(driver);
    }
    
    public FaqPage goToFaq() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( ExpectedConditions.elementToBeClickable(faq) );
        faq.click();
        return new FaqPage(driver);
    }
    
    public LoginPage goToLogin() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( ExpectedConditions.elementToBeClickable(login) );
        login.click();
        return new LoginPage(driver);
    }
    
    public HomePage goToHome() {
        clickMenuOption(home);
        return new HomePage(driver);
    }
    
    private void clickMenuOption(WebElement menuOption) {
        //menu hides the options -- responsive page
//        if(! menuOption.isDisplayed()) {
//            menuButton.click();
//        }
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until( ExpectedConditions.elementToBeClickable(menuOption) );
//        menuOption.click();
    }
}
