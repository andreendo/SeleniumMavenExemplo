package br.edu.utfpr.exemplomaven;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
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
    private static String CHROMEDRIVER_LOCATION = "/home/utfpr/install/selenium/chromedriver";
    
    private static int scId = 0;
    private int waitValue = 15;

    WebDriver driver;
    
    @BeforeClass
    public static void beforeClass() {
        File driver = new File(CHROMEDRIVER_LOCATION);
        if(driver.exists()) {
            System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
        } else {
            System.setProperty("webdriver.chrome.driver", "/home/guilherme/Ferramentas/selenium/chromedriver");
        }
    }
    
    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //Opcao headless para MacOS e Linux
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");
        
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(this.waitValue, TimeUnit.SECONDS);
    }
    
    @After
    public void after() {
        driver.close();
    }
    
    @Test
    public void faqTest() {
        driver.get("https://ration.io/");
        takeScreenShot("faqTest");
        
        WebElement faq = driver.findElement(By.linkText("FAQ"));
        faq.click();
        
        WebDriverWait wait = new WebDriverWait(driver, this.waitValue);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.id("faq")).isDisplayed() );
        takeScreenShot("faqTest");
        
        assertEquals("FAQ", driver.findElement(By.xpath("/html/body/div/div/div/h1")).getText() );
    }
    
    @Test
    public void shareTest() {
        acessar();
        driver.findElement(By.linkText("Things")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, this.waitValue);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"available-things\"]/div/div[1]/h1")).getText().equals("Things") );
        
        takeScreenShot("shareTest");
        
        driver.findElement(By.xpath("//*[@id=\"available-things\"]/div/div[1]/div/button")).click();
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"available-things\"]/div[2]/div[2]")).isDisplayed() );
        
        takeScreenShot("shareTest");
        
        assertEquals("Upload a new thing", driver.findElement(By.xpath("//*[@id=\"available-things\"]/div[2]/div[2]/div/div/h5")).getText() );
        // upload
    }
    
    @Test
    public void settingsTest() {
        WebDriverWait wait = new WebDriverWait(driver, this.waitValue);
        acessar();
        
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body")).isDisplayed() );
        
        driver.findElement(By.id("header-account-menu-link")).click();
        
        takeScreenShot("settingsTest");
        
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/div")).isDisplayed() );
        driver.findElement(By.linkText("Settings")).click();
        
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"account-overview\"]/div/h1")).getText().equals("My account") );
        takeScreenShot("settingsTest");
        
        driver.findElement(By.xpath("//*[@id=\"account-overview\"]/div/div[1]/div[2]/span/a")).click();
        
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"edit-profile\"]/div/h1")).getText().equals("Update personal info") );
        takeScreenShot("settingsTest");
        
        String novoNome = "Selenium " + ThreadLocalRandom.current().nextInt(1, 1001);
        WebElement fullName = driver.findElement(By.name("full-name"));
        fullName.clear();
        fullName.sendKeys(novoNome);
        takeScreenShot("settingsTest");
        
        driver.findElement(By.xpath("//*[@id=\"edit-profile\"]/div/form/div[2]/div[2]/div/button")).click();
        
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body")).isDisplayed() );
        WebElement name = driver.findElement(By.xpath("//*[@id=\"account-overview\"]/div/div[2]/div[2]/strong"));
        takeScreenShot("settingsTest");
        
        assertEquals(novoNome, name.getText());
    }
    
    private void takeScreenShot() {
        this.takeScreenShot("");
    }
    
    private void takeScreenShot(String name) {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + "-" + name + ".png"));
        } catch(IOException e) {}
    }
    
    private void acessar() {
        driver.get("https://ration.io/");
        takeScreenShot("acessar");
        
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        loginButton.click();
        
        WebDriverWait wait = new WebDriverWait(driver, this.waitValue);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.id("login")).isDisplayed() );
        
        driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input")).sendKeys("xpto-qwerty@bol.com.br");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input")).sendKeys("123mudar");
        driver.findElement(By.id("remember")).click();
        
        takeScreenShot("acessar");
        
        driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button")).click();
        //wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.id("friends")).isDisplayed() );
    }
}
