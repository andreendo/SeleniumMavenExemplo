package br.edu.utfpr.exemplomaven;

import br.edu.utfpr.exemplomaven.po.Setup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TelaInicialTest {

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
    public void redirecionarParaTelaDeLogin() {
        driver.get("https://ration.io/");
        WebElement botaoLogin = driver.findElement(By.linkText("Log in"));
        botaoLogin.click();
        takeScreenShot();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getTitle().startsWith("Ration") );
        takeScreenShot();
        assertEquals(driver.getCurrentUrl(), "https://ration.io/login");
    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            imageId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + imageId + ".png"));
        } catch(IOException e) {}
    }
}
