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
public class BugzillaTest {

    /**
     * Vc precisa identificar onde estah o chromedriver. Baixar de:
     * https://sites.google.com/a/chromium.org/chromedriver/downloads
     *
     * Versão utilizada do chromedriver: 2.35.528139
     */
    private static String CHROMEDRIVER_LOCATION = "X:/chromedriver/chromedriver.exe";

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
    public void loginInvalido(){
        driver.get("https://landfill.bugzilla.org/bugzilla-5.0-branch/");
        
        WebElement linkLogin = driver.findElement(By.id("login_link_top"));
        
        linkLogin.click();
        
        WebElement email = driver.findElement(By.id("Bugzilla_login_top"));
        
        email.sendKeys("asdsad@asdas.com");
        
        WebElement senha = driver.findElement(By.id("Bugzilla_password_top"));
        
        senha.sendKeys("asdsad");
        
        WebElement login = driver.findElement(By.id("log_in_top"));
        
        login.click();
        
        WebElement errorMsg = driver.findElement(By.id("error_msg"));
        assertEquals("The login or password you entered is not valid.", errorMsg.getText().trim());
    }
    
    
    @Test
    public void usuarioJaRegistrado(){
        driver.get("https://landfill.bugzilla.org/bugzilla-5.0-branch/createaccount.cgi");
        
        WebElement email = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        
        email.sendKeys("rafaelnsantos1@gmail.com");
        
        WebElement login = driver.findElement(By.id("send"));
        
        login.click();
        
        WebElement errorMsg = driver.findElement(By.id("error_msg"));
        
        assertEquals("There is already an account with the login name rafaelnsantos1@gmail.com.", errorMsg.getText().trim());
    
    }

    
    @Test 
    public void loginValido(){
        driver.get("https://landfill.bugzilla.org/bugzilla-5.0-branch/");
        
        WebElement linkLogin = driver.findElement(By.id("login_link_top"));
        
        linkLogin.click();
        
        WebElement email = driver.findElement(By.id("Bugzilla_login_top"));
        
        email.sendKeys("rafaelnsantos1@gmail.com");
        
        WebElement senha = driver.findElement(By.id("Bugzilla_password_top"));
        
        senha.sendKeys("abcd1234");
        
        WebElement login = driver.findElement(By.id("log_in_top"));
        
        login.click();
        
        driver.get("https://landfill.bugzilla.org/bugzilla-5.0-branch/userprefs.cgi");
        
        WebElement user = driver.findElement(By.xpath("//*[@id=\"subtitle\"]"));
        assertEquals("rafaelnsantos1@gmail.com", user.getText().trim());
    }
}
