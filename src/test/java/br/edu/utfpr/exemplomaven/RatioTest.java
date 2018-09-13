package br.edu.utfpr.exemplomaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
 */
public class RatioTest {

    private static int scId = 0;

    WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void testIncorrectPassword() throws InterruptedException {

        //logar com credenciais erradas
        
        driver.get("https://ration.io/login");

        WebElement inputEmail = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        inputEmail.sendKeys("jgs1884@outlook.com");
        
        WebElement inputPassword = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        inputPassword.sendKeys("12312121");
        
        WebElement btnLogin = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));
        btnLogin.click();

        WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/p/small"));
        assertEquals("The credentials you entered are not associated with an account. Please check your email and/or password and try again.", errorMsg.getText().trim());
         
    }
}
