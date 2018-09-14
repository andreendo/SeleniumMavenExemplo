package br.edu.utfpr.exemplomaven;

import br.edu.utfpr.exemplomaven.po.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

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
        driver = Setup.setup();
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void adicionarImagem() {
        LoginPage loginPage = new LoginPage(driver);
        AdicionarImagem upload = new AdicionarImagem(driver);
        Menu menu = new Menu(driver);

        loginPage.setEmail("liderandobr@gmail.com");
        loginPage.setSenha("Utfpr@2018");
        loginPage.submit();

        takeScreenShot();

        HomePage homePage = loginPage.esperarHomeCarregar();

        takeScreenShot();

        assertEquals(homePage.getUrl(), "https://ration.io/friends");

        menu.buttonItemThings();
        takeScreenShot();
        upload.buttonAddItem();
        takeScreenShot();
        upload.buttonSelectImage();
        takeScreenShot();
        upload.setDirectoryUploadImage("/tmp/imgTest1.png");
        takeScreenShot();
        upload.setNameImage("Imagem de Teste 01");
        takeScreenShot();
        upload.buttonShareItem();
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
