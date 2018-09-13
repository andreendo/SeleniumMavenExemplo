package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedPage extends RationBasePage {

    @FindBy(tagName = "h1")
    WebElement title;

//    @FindBy(css = ".ajax-form .form-group:first-child input")
//    WebElement inputEmail;
//
//    @FindBy(css = ".ajax-form .form-group:nth-child(2) input")
//    WebElement inputSenha;
//
//    @FindBy(css = ".ajax-form .form-group:last-child")
//    WebElement btnLogin;


    public LoggedPage(WebDriver driver) {
        super(driver);
    }
}
