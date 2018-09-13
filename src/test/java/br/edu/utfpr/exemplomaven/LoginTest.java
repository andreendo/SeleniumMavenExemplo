package br.edu.utfpr.exemplomaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        driver.get("https://ration.io/login");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        emailInput.sendKeys("liderandobr@gmail.com");
        WebElement senhaInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        senhaInput.sendKeys("Utfpr@2018");
        WebElement botaoLogin = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));
        botaoLogin.submit();
        takeScreenShot();



        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h1")) != null);

        takeScreenShot();

        assertEquals(driver.getCurrentUrl(), "https://ration.io/friends");
    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            imageId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + imageId + ".png"));
        } catch(IOException e) {}
    }

}
