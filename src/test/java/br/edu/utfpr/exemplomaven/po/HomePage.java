package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

}
