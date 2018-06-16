package br.edu.utfpr.exemplomaven;


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
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
 */
public class RationTest {

    /**
     * Vc precisa identificar onde estah o chromedriver. Baixar de:
     * https://sites.google.com/a/chromium.org/chromedriver/downloads
     *
     * Versão utilizada do chromedriver: 2.35.528139
     */
    private static String CHROMEDRIVER_LOCATION = "/Applications/MAMP/htdocs/chromedriver";
    
    private static int scId = 0;

    WebDriver driver;
    
    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
    }
    
    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //Opcao headless para MacOS e Linux
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
    public void testLogin() {
        driver.get("https://ration.io/");
        
        WebElement botaoLogin = driver.findElement(By.cssSelector("#page-header > div > a:nth-child(2)"));
        botaoLogin.click();
        
        WebElement username = driver.findElement(By.cssSelector("#login > div > div > form > div:nth-child(1) > input"));
        username.sendKeys("guilherme_mattiello@hotmail.com");
        
        WebElement password = driver.findElement(By.cssSelector("#login > div > div > form > div:nth-child(2) > input"));
        password.sendKeys("utfprteste");
        
        WebElement enviar = driver.findElement(By.cssSelector("#login > div > div > form > div:nth-child(4) > button"));
        enviar.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().toLowerCase().contains("friends") );
        
        assertTrue(driver.getCurrentUrl().contains("friends"));
    }
    
    @Test
    public void testAlteraInfoPessoal() {
        driver.get("https://ration.io/");
        
        WebElement botaoLogin = driver.findElement(By.cssSelector("#page-header > div > a:nth-child(2)"));
        botaoLogin.click();
        
        WebElement username = driver.findElement(By.cssSelector("#login > div > div > form > div:nth-child(1) > input"));
        username.sendKeys("guilherme_mattiello@hotmail.com");
        
        WebElement password = driver.findElement(By.cssSelector("#login > div > div > form > div:nth-child(2) > input"));
        password.sendKeys("utfprteste");
        
        WebElement enviar = driver.findElement(By.cssSelector("#login > div > div > form > div:nth-child(4) > button"));
        enviar.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().toLowerCase().contains("friends") );
        
        WebElement accountButton = driver.findElement(By.cssSelector("#header-account-menu-link"));
        accountButton.click();
        
        WebElement settingsButton = driver.findElement(By.cssSelector("#page-header > div > div > div > a:nth-child(1)"));
        settingsButton.click();
        
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().toLowerCase().contains("account") );
        
        WebElement editProfileButton = driver.findElement(By.cssSelector("#account-overview > div > div:nth-child(3) > div:nth-child(2) > span > a"));
        editProfileButton.click();
        
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().toLowerCase().contains("profile") );
        
        WebElement fullName = driver.findElement(By.id("full-name"));
        fullName.clear();
        fullName.sendKeys("Guilherme Mattiello2");
        
        WebElement saveButton = driver.findElement(By.cssSelector("#edit-profile > div > form > div:nth-child(2) > div:nth-child(2) > div > button"));
        saveButton.click();
        
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getPageSource().contains("My account") );
        
        assertTrue(driver.getPageSource().contains("Guilherme Mattiello2"));
    }
    
    @Test
    public void testLogoff() {
        driver.get("https://ration.io/");
        
        WebElement botaoLogin = driver.findElement(By.cssSelector("#page-header > div > a:nth-child(2)"));
        botaoLogin.click();
        
        WebElement username = driver.findElement(By.cssSelector("#login > div > div > form > div:nth-child(1) > input"));
        username.sendKeys("guilherme_mattiello@hotmail.com");
        
        WebElement password = driver.findElement(By.cssSelector("#login > div > div > form > div:nth-child(2) > input"));
        password.sendKeys("utfprteste");
        
        WebElement enviar = driver.findElement(By.cssSelector("#login > div > div > form > div:nth-child(4) > button"));
        enviar.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().toLowerCase().contains("friends") );
        
        WebElement accountButton = driver.findElement(By.cssSelector("#header-account-menu-link"));
        accountButton.click();
        
        WebElement logoffButton = driver.findElement(By.cssSelector("#page-header > div > div > div > a:nth-child(2)"));
        logoffButton.click();
        
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getPageSource().contains("Sign in to your account") );
        
        assertTrue(driver.getPageSource().contains("Sign in to your account"));
    }
    
}
