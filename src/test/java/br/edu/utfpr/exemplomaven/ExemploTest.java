package br.edu.utfpr.exemplomaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.swing.text.html.HTML;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
public class ExemploTest {

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
        chromeOptions.addArguments("--incognito");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void after() {
        driver.close();
    }

    //@Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com.br/");
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("teste de software");

        takeScreenShot();

        searchInput.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.getTitle().toLowerCase().startsWith("teste"));

        takeScreenShot();

        assertTrue(driver.getTitle().startsWith("teste de software"));
    }

    //@Test
    public void test02() {
        driver.get("https://github.com/");

        WebElement signUpButton = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div[2]/div[1]/form/button"));
        signUpButton.click();

        //check msg: "There were problems creating your account."
        WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/div[1]"));
        assertEquals("There were problems creating your account.", errorMsg.getText().trim());

        //check msg: "Login can't be blank"
        WebElement errorMsg02 = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/auto-check[1]/dl/dd[2]"));
        assertEquals("Login can't be blank", errorMsg02.getText().trim());

        //fill the username
        WebElement username = driver.findElement(By.id("user_login"));
        username.sendKeys("andreendo22");

        //click on button "create account"
        WebElement caButton = driver.findElement(By.id("signup_button"));
        caButton.click();

        try {
            errorMsg02 = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/auto-check[1]/dl/dd[2]"));
            fail();
        } catch (NoSuchElementException e) {
        }
        driver.close();
    }
    
    //@Test
    public void testSignUpRation(){
        driver.get("https://ration.io/");
        
        driver.findElement(By.linkText("Sign up")).click();
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Create an account"));
        
        WebElement button = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button"));
        button.click();
        String errorMsg = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[1]/div")).getText();
        assertEquals("Please enter your full name.", errorMsg);
        
    }
    
    @Test
    public void testFAQRation() throws InterruptedException{
        driver.get("https://ration.io/");
        
        driver.findElement(By.linkText("FAQ")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("click here")).click();
        
        String supportTitle = driver.findElement(By.tagName("body")).getText();
        
        assertTrue(supportTitle.contains("Sails/Node.js Support"));
        
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
