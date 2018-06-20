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

/**
 *
 * @author andreendo
 */
public class ExemploTest {

    /**
     * Vc precisa identificar onde estah o chromedriver. Baixar de:
     * https://sites.google.com/a/chromium.org/chromedriver/downloads
     *
     * Versão utilizada do chromedriver: 2.35.528139
     */
    private static String CHROMEDRIVER_LOCATION = "C:\\chromedriver.exe";

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

//    @Test
//    public void testGoogleSearch() {
//        driver.get("https://www.google.com.br/");
//        WebElement searchInput = driver.findElement(By.name("q"));
//        searchInput.sendKeys("teste de software");
//
//        takeScreenShot();
//
//        searchInput.submit();
//
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.getTitle().toLowerCase().startsWith("teste"));
//
//        takeScreenShot();
//
//        assertTrue(driver.getTitle().startsWith("teste de software"));
//    }
    
    
    @Test
    public void testNavegation() {
        driver.get("https://ration.io/");

        takeScreenShot();

        driver.findElement(By.xpath("//*[@id='page-header']/div/a[1]")).click();

        assertTrue(driver.getCurrentUrl().contains("https://ration.io/faq"));
    }

    @Test
    public void testEmail() throws InterruptedException, IOException {
        driver.get("https://ration.io/");

        takeScreenShot();

        driver.findElement(By.xpath("//*[@id='page-header']/div/a[1]")).click();

        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.linkText("Forgot your password?")).click();
        driver.findElement(By.name("email-address")).click();
        driver.findElement(By.name("email-address")).clear();
        driver.findElement(By.name("email-address")).sendKeys("teste@teste.com");
        driver.findElement(By.xpath("//div[@id='forgot-password']/div/div/form/div[2]/button/span")).click();

        WebElement webElement = driver.findElement(By.xpath("//*[@id='forgot-password']/div/div/p[3]/a"));
        assertTrue(webElement.isDisplayed());
    }

    @Test
    public void testLoginFail() {
        driver.get("https://ration.io/");
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.xpath("//input[@type='email']")).clear();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("teste@teste.com");
        driver.findElement(By.xpath("//input[@type='password']")).clear();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("teste");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        WebElement webElement = driver.findElement(By.xpath("//*[@id='login']/div/div/form/p"));
        assertTrue(webElement.isDisplayed());
    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch (IOException e) {
        }
    }

}
