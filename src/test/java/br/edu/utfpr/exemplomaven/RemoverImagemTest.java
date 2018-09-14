package br.edu.utfpr.exemplomaven;

import br.edu.utfpr.exemplomaven.po.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RemoverImagemTest {

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
    public void removerImagem() {
        LoginPage loginPage = new LoginPage(driver);
        RemoverImagem removePage = new RemoverImagem(driver);
        Menu menu = new Menu(driver);

        loginPage.setEmail("liderandobr@gmail.com");
        loginPage.setSenha("Utfpr@2018");
        loginPage.submit();

        takeScreenShot();

        HomePage homePage = loginPage.esperarHomeCarregar();

        takeScreenShot();

        assertEquals(homePage.getUrl(), "https://ration.io/friends");

        menu.buttonItemThings();
        removePage.remover();
        takeScreenShot();
        removePage.buttonConfirmRemove();
        takeScreenShot();
        removePage.esperarHomeCarregar();

        takeScreenShot();

        assertEquals(homePage.getUrl(), "https://ration.io/things");


    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            imageId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + imageId + ".png"));
        } catch(IOException e) {}
    }
}
