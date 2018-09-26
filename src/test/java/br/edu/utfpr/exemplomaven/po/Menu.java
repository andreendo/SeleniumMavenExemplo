package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Menu extends BasePage {

    @FindBy(tagName = "h1")
    WebElement title;

    public Menu(WebDriver driver) {
        super(driver);
    }
}