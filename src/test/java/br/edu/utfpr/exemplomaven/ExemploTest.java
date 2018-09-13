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
        WebDriverManager.chromedriver().proxy("10.20.10.50:3128").setup();
    }
    
    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");
        
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.get("https://ration.io/");
        WebElement botaoLogin = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        botaoLogin.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"login\"]/div/h1")).
                                                                    getText().toLowerCase().startsWith("sign in") );
        WebElement campoEmail = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        campoEmail.sendKeys("softwareutfpr@gmail.com");
        WebElement campoSenha = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        campoSenha.sendKeys("teste");
        WebElement campoSignIn = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));
        campoSignIn.click();
    }
    
    @After
    public void after() {
        driver.close();
    }
    /*
    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com.br/");
        WebElement searchInput = driver.findElement(By.name("q"));
        
        searchInput.sendKeys("teste de software");
        
        takeScreenShot();
        
        searchInput.submit();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getTitle().toLowerCase().startsWith("teste") );
        
        takeScreenShot();
        
        assertTrue(driver.getTitle().startsWith("teste de software"));
    }
    */
    @Test
    public void testeLogin(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h1")).
                                                                    getText().toLowerCase().startsWith("friends") );
        
        
        takeScreenShot();
    }
    
    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }
}
