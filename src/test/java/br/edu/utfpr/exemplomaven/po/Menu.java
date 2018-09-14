package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Menu extends BasePage {
    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[1]")
    WebElement itemFriends;

    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[2]")
    WebElement itemThings;

    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[3]")
    WebElement itemHelp;

    @FindBy(xpath = "//*[@id=\"page-header\"]/div/div")
    WebElement itemAccounts;

    @FindBy(xpath = "//*[@id=\"page-header\"]/div/a[4]")
    WebElement itemSettings;

    @FindBy(xpath = "//*[@id=\"page-header\"]/div/div/div/a[2]")
    WebElement itemLogout;

    public Menu(WebDriver driver) {
        super(driver);
    }

    public Menu buttonItemFriends() {
        this.itemFriends.click();
        return this;
    }

    public Menu buttonItemThings() {
        this.itemThings.click();
        return this;
    }

    public Menu buttonItemHelp() {
        this.itemHelp.click();
        return this;
    }

    public Menu buttonItemAccounts() {
        this.itemAccounts.click();
        return this;
    }

    public Menu buttonItemSettings() {
        this.itemSettings.click();
        return this;
    }

    public Menu buttonItemLogout() {
        this.itemLogout.click();
        return this;
    }

    public HomePage esperarHomeCarregar() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().equals("https://ration.io"));
        return new HomePage(driver);
    }

}
