package br.edu.utfpr.exemplomaven.po.loggedIn;

import br.edu.utfpr.exemplomaven.po.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RationLoggedInBasePage extends BasePage {


    @FindBy(tagName = "h1")
    WebElement title;

    MenuLoggedIn menu;

    public RationLoggedInBasePage(WebDriver driver) {
        super(driver);
        menu = new MenuLoggedIn(driver);
    }

    public String getTitle() {
        return title.getText();
    }

    public MenuLoggedIn getMenu() {
        return menu;
    }
}
