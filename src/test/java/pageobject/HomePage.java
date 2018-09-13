package pageobject;

import org.openqa.selenium.WebDriver;

public class HomePage extends RationBasePage {
    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("http://ration.io");
    }
}
