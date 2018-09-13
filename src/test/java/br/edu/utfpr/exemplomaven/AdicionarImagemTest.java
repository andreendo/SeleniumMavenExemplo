package br.edu.utfpr.exemplomaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdicionarImagemTest {

    private static int imageId = 0;

    WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        driver = TestUtils.setup();
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void adicionarImagem() {
        driver.get("https://ration.io/login");
        TestUtils.login(driver);
        WebElement botaoThings = driver.findElement(By.linkText("Things"));
        botaoThings.click();
        WebElement botaoAddItem = driver.findElement(By.xpath("//*[@id=\"available-things\"]/div/div[1]/div/button"));
        botaoAddItem.click();
        WebElement botaoSelectImage = driver.findElement(By.cssSelector("#available-things > div.modal.fade.clog-modal.show > div.modal-dialog.custom-width > div > form > div.modal-body > div > div > div > div.col.d-flex-column.justify-content-center > span > input"));
        botaoSelectImage.click();
        WebElement uploadImage = driver.findElement(By.xpath("//*[@id=\"available-things\"]/div[2]/div[2]/div/form/div[1]/div/div/div/div[2]/span/input"));
        uploadImage.sendKeys("/tmp/imgTest1.png");
        WebElement nameImage = driver.findElement(By.cssSelector("#available-things > div.modal.fade.clog-modal.show > div.modal-dialog.custom-width > div > form > div.modal-body > div:nth-child(2) > input"));
        nameImage.sendKeys("Imagem de Teste 01");
        WebElement botaoShareItem = driver.findElement(By.cssSelector("#available-things > div.modal.fade.clog-modal.show > div.modal-dialog.custom-width > div > form > div.modal-footer.flex-row-reverse.justify-content-start > button.btn.ajax-button.btn.btn-primary.ml-1 > span"));
        botaoShareItem.click();

        takeScreenShot();



        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().equals("https://ration.io/things"));

        takeScreenShot();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"available-things\"]/div/div[2]/div/span")) != null);
    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            imageId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + imageId + ".png"));
        } catch(IOException e) {}
    }
}
