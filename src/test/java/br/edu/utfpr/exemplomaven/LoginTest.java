package br.edu.utfpr.exemplomaven;

import br.edu.utfpr.exemplomaven.po.HomePage;
import br.edu.utfpr.exemplomaven.po.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoginTest {

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
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.setEmail("liderandobr@gmail.com");
        loginPage.setSenha("Utfpr@2018");
        loginPage.submit();

        takeScreenShot();

        HomePage homePage = loginPage.esperarHomeCarregar();

        takeScreenShot();

        assertEquals(homePage.getUrl(), "https://ration.io/friends");
    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            imageId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + imageId + ".png"));
        } catch(IOException e) {}
    }

}
