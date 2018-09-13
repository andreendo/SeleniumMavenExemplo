package br.edu.utfpr.exemplomaven.po.loggedOut;

import org.openqa.selenium.WebDriver;

public class HomePage extends RationLoggedOutBasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://ration.io");
    }


}
