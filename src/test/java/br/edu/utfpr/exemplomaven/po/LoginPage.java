package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[1]/input")
    WebElement email;

    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[2]/input")
    WebElement senha;

    @FindBy(xpath = "//*[@id=\"login\"]/div/div/form/div[4]/button")
    WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get("https://ration.io/login");
    }

    public LoginPage setEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public LoginPage setSenha(String senha) {
        this.senha.sendKeys(senha);
        return this;
    }

    public LoginPage submit() {
        this.submit.submit();
        return this;
    }

    public HomePage esperarHomeCarregar() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h1")) != null);
        return new HomePage(driver);
    }
}
