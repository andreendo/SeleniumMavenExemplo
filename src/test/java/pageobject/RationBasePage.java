package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RationBasePage extends BasePage{
    @FindBy(tagName = "h2")
    WebElement title;

    Menu menu;

    public RationBasePage(WebDriver driver) {
        super(driver);
        menu = new Menu(driver);
    }

    public Menu getMenu() {
        return menu;
    }

    public String getTitle() {
        return title.getText();
    }
}
