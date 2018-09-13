package br.edu.utfpr.exemplomaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class RemoverImagemTest {

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
        TestUtils.uploadImage(driver, 2);
        WebElement botaoRemover = driver.findElement(By.xpath("//*[@id=\"available-things\"]/div/div[2]/div/div"));
        botaoRemover.click();
        WebElement botaoConfirmarRemocao = driver.findElement(By.xpath("//*[@id=\"available-things\"]/div[2]/div[2]/div/form/div[2]/button[2]/span"));
        botaoConfirmarRemocao.click();

        takeScreenShot();



        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().equals("https://ration.io/things"));

        takeScreenShot();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"available-things\"]/div/div[2]/div/span")) == null);
    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            imageId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + imageId + ".png"));
        } catch(IOException e) {}
    }
}
