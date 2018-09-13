package br.edu.utfpr.exemplomaven;

import br.edu.utfpr.exemplomaven.po.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RatioTest {

    WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("headless");
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
        driver.get("https://ration.io/login");
        LoginPage login = new LoginPage(driver);
        
        login
            .setEmail("jgs1884@outlook.com")
            .setPassword("123456789")
            .submit();

        assertEquals("The credentials you entered are not associated with an account. Please check your email and/or password and try again.", login.getErrorMsg());  
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
    
    @Test
    public void testContact() throws InterruptedException {
        
        driver.get("https://ration.io/login");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        emailInput.sendKeys("jgs1884@outlook.com");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        passwordInput.sendKeys("123456");

        WebElement btnLogin = driver.findElement(By.className("ajax-button"));
        btnLogin.click();    
                
        driver.findElement(By.cssSelector("#page-header > div > a[href=\"/contact\"]")).click();
        
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("full-name"))).sendKeys("teste");
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("email-address"))).sendKeys("teste@teste.com");
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("topic"))).sendKeys("price teste");
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("message"))).sendKeys("hello teste");

        WebElement btnSend = driver.findElement(By.className("ajax-button"));
        btnSend.click();
        
        WebElement h1 = driver.findElement(By.cssSelector("#contact > div > h1"));
        WebElement subText = driver.findElement(By.xpath("//*[@id=\"contact\"]/div/div/p[2]"));
        
                
        assertEquals("Get in touch", h1.getText().trim());
        assertEquals("We have received your message, and someone from our team will get back to you soon.", subText.getText().trim());   
    }
    
}
