package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author CINTIA
 */
public class RationPage extends BasePage {

    @FindBy(tagName = "h2")
    WebElement titulo;

    Menu menu;

    public RationPage(WebDriver driver) {
        super(driver);
        menu = new Menu(driver);
    }

    public Menu selecionaItemMenu() {
        return menu;
    }

    public String getTitle() {
        return titulo.getText();
    }

}
