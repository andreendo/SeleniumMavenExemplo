package br.edu.utfpr.exemplomaven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestUtils {

    public static WebDriver setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");

        WebDriver webdriver = new ChromeDriver(chromeOptions);

        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return webdriver;
    }

    public static void login(WebDriver driver) {
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        emailInput.sendKeys("liderandobr@gmail.com");
        WebElement senhaInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        senhaInput.sendKeys("Utfpr@2018");
        WebElement botaoLogin = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));
        botaoLogin.submit();
        WebElement botaoAcount = driver.findElement(By.id("header-account-menu-link"));
        botaoAcount.click();
    }

    public static void uploadImage(WebDriver driver, int imgId) {
        WebElement botaoThings = driver.findElement(By.linkText("Things"));
        botaoThings.click();
        WebElement botaoAddItem = driver.findElement(By.xpath("//*[@id=\"available-things\"]/div/div[1]/div/button"));
        botaoAddItem.click();
        WebElement botaoSelectImage = driver.findElement(By.cssSelector("#available-things > div.modal.fade.clog-modal.show > div.modal-dialog.custom-width > div > form > div.modal-body > div > div > div > div.col.d-flex-column.justify-content-center > span > input"));
        botaoSelectImage.click();
        WebElement uploadImage = driver.findElement(By.xpath("//*[@id=\"available-things\"]/div[2]/div[2]/div/form/div[1]/div/div/div/div[2]/span/input"));
        uploadImage.sendKeys("/tmp/imgTest1.png");
        WebElement nameImage = driver.findElement(By.cssSelector("#available-things > div.modal.fade.clog-modal.show > div.modal-dialog.custom-width > div > form > div.modal-body > div:nth-child(2) > input"));
        nameImage.sendKeys("Imagem de Teste " + imgId);
        WebElement botaoShareItem = driver.findElement(By.cssSelector("#available-things > div.modal.fade.clog-modal.show > div.modal-dialog.custom-width > div > form > div.modal-footer.flex-row-reverse.justify-content-start > button.btn.ajax-button.btn.btn-primary.ml-1 > span"));
        botaoShareItem.click();
    }
}
