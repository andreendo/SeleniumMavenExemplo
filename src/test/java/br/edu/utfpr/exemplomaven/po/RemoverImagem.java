package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoverImagem extends BasePage {
    @FindBy(xpath = "//*[@id=\"available-things\"]/div/div[2]/div[1]/img")
    WebElement componentImg;

    @FindBy(xpath = "//*[@id=\"available-things\"]/div/div[2]/div[1]/div")
    WebElement componentDiv;

    @FindBy(xpath = "//*[@id=\"available-things\"]/div[2]/div[2]/div/form/div[2]/button[2]")
    WebElement buttonConfirmRemove;


    public RemoverImagem(WebDriver driver) {
        super(driver);
    }

    public void remover(){
        Actions action = new Actions(driver);
        action
                .moveToElement(this.componentImg)
                .moveToElement(this.componentDiv)
                .click()
                .build()
                .perform();
    }

    public RemoverImagem buttonConfirmRemove() {
        this.buttonConfirmRemove.click();
        return this;
    }

    public HomePage esperarHomeCarregar() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().equals("https://ration.io/things"));
        return new HomePage(driver);
    }

}
