package br.edu.utfpr.exemplomaven.po.loggedOut;

import br.edu.utfpr.exemplomaven.po.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RationLoggedOutBasePage extends BasePage {


    @FindBy(tagName = "h1")
    WebElement title;

    MenuLoggedOut menu;

    public RationLoggedOutBasePage(WebDriver driver) {
        super(driver);
        menu = new MenuLoggedOut(driver);
    }

    public String getTitle() {
        return title.getText();
    }

    public MenuLoggedOut getMenu() {
        return menu;
    }
}
