package br.edu.utfpr.po;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author andreendo
 */
public class HomePage extends RationBasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://ration.io/");
    }
}
