package br.edu.utfpr.exemplomaven;


import br.edu.utfpr.exemplomaven.po.LoginPage;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author andreendo
 */
public class rationTests {

    private static int scId = 0;

    WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void logarOnRatio(String pass) {
        String urlLogin = "https://ration.io/login";
        driver.get(urlLogin);
        LoginPage loginpage = new LoginPage(driver);
        if (driver.getCurrentUrl().equals(urlLogin)) {

            loginpage.getLoginInput().sendKeys("r144@hotmail.com");

            loginpage.getPasswordInput().sendKeys(pass);
            loginpage.getSignButton().click();
            WebDriverWait w = new WebDriverWait(driver, 5);
            w.until(ExpectedConditions.urlToBe("https://ration.io/friends"));
            assertEquals("https://ration.io/friends", driver.getCurrentUrl());
        }

    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void AlterPasswordTest() {

        logarOnRatio("axicaba");
        WebElement AccountButton = driver.findElement(By.id("header-account-menu-link"));//account
        AccountButton.click();

        driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/div/a[1]")).click();//settings
        driver.findElement(By.xpath("//*[@id=\"account-overview\"]/div/div[4]/div[2]/span/a")).click();//change password
        driver.findElement(By.name("password")).sendKeys("axicaba");//password
        driver.findElement(By.name("confirm-password")).sendKeys("axicaba");//confirm password
        driver.findElement(By.xpath("//*[@id=\"change-password\"]/div/form/div[2]/div[2]/div/button/span")).click();//button password save
        WebDriverWait w = new WebDriverWait(driver, 5);
        w.until(ExpectedConditions.urlToBe("https://ration.io/account"));
        assertEquals("https://ration.io/account", driver.getCurrentUrl());
    }

    @Test
    public void navigationTest() {
        logarOnRatio("axicaba");
        driver.findElement(By.linkText("Friends")).click();
        assertEquals("Friends", driver.findElement(By.tagName("h1")).getText());
        driver.findElement(By.linkText("Things")).click();
        assertEquals("Things", driver.findElement(By.tagName("h1")).getText());
        driver.findElement(By.linkText("Help")).click();
        assertEquals("Get in touch", driver.findElement(By.tagName("h1")).getText());

    }
}