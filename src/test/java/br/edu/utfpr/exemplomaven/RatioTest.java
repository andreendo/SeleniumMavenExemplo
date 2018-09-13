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
        chromeOptions.addArguments("headless");
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
    public void testInviteFriends() throws InterruptedException {
        
        driver.get("https://ration.io/login");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        emailInput.sendKeys("jgs1884@outlook.com");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        passwordInput.sendKeys("123456");

        WebElement btnLogin = driver.findElement(By.className("ajax-button"));
        btnLogin.click();
        
        driver.findElement(By.cssSelector("#friends > div > div.page-header.text-center > div > button")).click();

        WebElement inputName = driver.findElement(By.xpath("//*[@id=\"friends\"]/div[2]/div[2]/div/form/div[1]/div[1]/div/div[1]/input"));
        inputName.sendKeys("xaxaxa");

        WebElement inputEmail = driver.findElement(By.xpath("//*[@id=\"friends\"]/div[2]/div[2]/div/form/div[1]/div[1]/div/div[2]/input"));
        inputEmail.sendKeys("xaxaxa@email.com");

        WebElement btnAdd = driver.findElement(By.xpath("//*[@id=\"friends\"]/div[2]/div[2]/div/form/div[2]/button[1]"));
        btnAdd.click();
   
        List<WebElement> tbody = driver.findElements(By.xpath("//*[@id=\"friends\"]/div/div[2]/table/tbody/tr"));
        int size = tbody.size()+1;
        
        String pathTd = "";
        if (size == 1) {
            pathTd = "//*[@id=\"friends\"]/div/div[2]/table/tbody/tr/td[1]/strong";
        } else {
            pathTd = "//*[@id=\"friends\"]/div/div[2]/table/tbody/tr["+size+"]/td[1]/strong";
        }
        
        WebElement userInvited = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(pathTd)));
        assertEquals("xaxaxa", userInvited.getText().trim());
    }
}
