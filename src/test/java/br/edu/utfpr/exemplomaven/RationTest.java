package br.edu.utfpr.exemplomaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RationTest {
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
        driver.get("https://ration.io/");
        takeScreenShot();
    }

    @After
    public void after() {
        driver.close();
    }

    //@Test
    public void testLogin() {
        doLogin();

        assertTrue(driver.getCurrentUrl().contains("friends"));
        takeScreenShot();
    }

    @Test
    public void testChangeAccountSettingName() {
        doLogin();

        WebElement btnAccount = driver.findElement(By.linkText("Account"));
        btnAccount.click();

        WebElement btnSettings = driver.findElement(By.linkText("Settings"));
        btnSettings.click();

        WebElement pageWrap = driver.findElement(By.id("page-wrap"));
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.textToBePresentInElement(pageWrap, "My account"));

        WebElement btnEditProfile = driver.findElement(By.linkText("Edit profile"));
        btnEditProfile.click();

        pageWrap = driver.findElement(By.id("page-wrap"));
        wait.until(ExpectedConditions.textToBePresentInElement(pageWrap, "Update personal info"));

        WebElement inputName = driver.findElement(By.id("full-name"));
        inputName.click();
        inputName.clear();
        inputName.sendKeys("Josicleysson Antunes da Silva");
        inputName.submit();

        //-----

        btnEditProfile = driver.findElement(By.linkText("Edit profile"));
        btnEditProfile.click();
        pageWrap = driver.findElement(By.id("page-wrap"));
        wait.until(ExpectedConditions.textToBePresentInElement(pageWrap, "Update personal info"));

        inputName = driver.findElement(By.id("full-name"));
        inputName.click();
        inputName.clear();
        inputName.sendKeys("Vinicius Baroni Soares");
        inputName.submit();

        pageWrap = driver.findElement(By.id("account-overview"));
        wait.until(ExpectedConditions.textToBePresentInElement(pageWrap, "My account"));

        assertTrue(driver.getPageSource().contains("Vinicius Baroni Soares"));
    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }

    private void doLogin() {
        WebElement btnLogin = driver.findElement(By.linkText("Log in"));
        btnLogin.click();
        takeScreenShot();

        WebElement inputEmail = driver.findElement(By.cssSelector(".ajax-form .form-group:first-child input"));
        inputEmail.sendKeys("vinibaronisoares@gmail.com");

        WebElement inputSenha = driver.findElement(By.cssSelector(".ajax-form .form-group:nth-child(2) input"));
        inputSenha.sendKeys("abacaxi");

        WebElement btnSubmit = driver.findElement(By.cssSelector(".ajax-form .form-group:last-child button"));
        takeScreenShot();

        btnSubmit.click();

        WebElement friends = driver.findElement(By.id("friends"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(friends, "Friends"));
    }
}
