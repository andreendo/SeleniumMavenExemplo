package br.edu.utfpr.exemplomaven;

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

public class LogoutTest {

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
    public void testLogout() {
        driver.get("https://ration.io/login");
        TestUtils.login(driver);
        WebElement botaoLogout = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/div/a[2]"));
        botaoLogout.click();
        takeScreenShot();



        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().equals("https://ration.io/login"));

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
