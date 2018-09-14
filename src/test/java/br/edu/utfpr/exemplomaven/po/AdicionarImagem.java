package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdicionarImagem extends BasePage {
    @FindBy(linkText = "Things")
    WebElement botaoThings;

    @FindBy(xpath = "//*[@id=\"available-things\"]/div/div[1]/div/button")
    WebElement botaoAddItem;

    @FindBy(css = "#available-things > div.modal.fade.clog-modal.show > div.modal-dialog.custom-width > div > form > div.modal-body > div > div > div > div.col.d-flex-column.justify-content-center > span > input")
    WebElement botaoSelectImage;

    @FindBy(xpath = "//*[@id=\"available-things\"]/div[2]/div[2]/div/form/div[1]/div/div/div/div[2]/span/input")
    WebElement uploadImage;

    @FindBy(css = "#available-things > div.modal.fade.clog-modal.show > div.modal-dialog.custom-width > div > form > div.modal-body > div:nth-child(2) > input")
    WebElement nameImage;

    @FindBy(css = "#available-things > div.modal.fade.clog-modal.show > div.modal-dialog.custom-width > div > form > div.modal-footer.flex-row-reverse.justify-content-start > button.btn.ajax-button.btn.btn-primary.ml-1 > span")
    WebElement botaoShareItem;

    public AdicionarImagem(WebDriver driver) {
        super(driver);
    }

    public AdicionarImagem setDirectoryUploadImage(String uploadImage) {
        this.uploadImage.sendKeys(uploadImage);
        return this;
    }

    public AdicionarImagem setNameImage(String nameImage) {
        this.nameImage.sendKeys(nameImage);
        return this;
    }

    public AdicionarImagem buttonThings() {
        this.botaoThings.click();
        return this;
    }

    public AdicionarImagem buttonAddItem() {
        this.botaoAddItem.click();
        return this;
    }

    public AdicionarImagem buttonSelectImage() {
        this.botaoSelectImage.click();
        return this;
    }

    public AdicionarImagem buttonShareItem() {
        this.botaoShareItem.click();
        return this;
    }

    public HomePage esperarHomeCarregar() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().equals("https://ration.io/things"));
        return new HomePage(driver);
    }

}
