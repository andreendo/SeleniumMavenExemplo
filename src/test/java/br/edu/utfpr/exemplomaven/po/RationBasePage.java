package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RationBasePage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement title;

    private Menu menu;

    public RationBasePage(WebDriver driver) {
        super(driver);
    }

    public Menu getMenu() {
        return menu;
    }

    public String getTitle() {
        return title.getText();
    }
}