package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends RationBasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://ration.io");
    }

}

