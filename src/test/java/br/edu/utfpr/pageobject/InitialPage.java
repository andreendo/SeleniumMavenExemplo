package br.edu.utfpr.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InitialPage extends BasePage {

    WebElement logInButton;

    public InitialPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLogin() {
        logInButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"login\"]/div/h1")).
                getText().toLowerCase().startsWith("sign in"));
        return new LoginPage(driver);
    }

}
