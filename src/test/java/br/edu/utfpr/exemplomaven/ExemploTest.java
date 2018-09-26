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
import org.openqa.selenium.TimeoutException;
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
    public void testGoogleSearch() throws IOException, TimeoutException{
        driver.get("https://www.google.com.br/");
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("testedesoftware");
        
        takeScreenShot();
        
        searchInput.submit();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getTitle().toLowerCase().startsWith("teste") );
        
        takeScreenShot();
        
        assertTrue(driver.getTitle().startsWith("testedesoftware"));
    }
    
    @Test
    public void testLogin() {
        driver.get("https://ration.io/");

        WebElement signUpBtn = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/a"));
        signUpBtn.click();
        
        takeScreenShot();

        WebElement username = driver.findElement(By.id("full-name"));
        username.sendKeys("adolpheraJoga10");
                
        WebElement email = driver.findElement(By.id("email-address"));
        email.sendKeys("acn2002@live.com");
 
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("pass43@21");

        WebElement confirmPass = driver.findElement(By.id("confirm-password"));
        confirmPass.sendKeys("pass43@21");

        
        WebElement ticar = driver.findElement(By.id("terms-agreement"));
        ticar.click();    
        
        WebElement criarConta = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button"));
        criarConta.click(); 
        
        takeScreenShot();

    }


    @Test
    public void testLogar() {
        driver.get("https://ration.io/");

        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        loginBtn.click();
        
        takeScreenShot();

        WebElement email = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        email.sendKeys("acn2002@live.com");
 
        WebElement pass = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        pass.sendKeys("pass43@21");

        WebElement entrar = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button/span"));
        entrar.click(); 
        
        takeScreenShot();

    }
    

    @Test
    public void testSair() {
        driver.get("https://ration.io/");

        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        loginBtn.click();
        
        takeScreenShot();

        WebElement email = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        email.sendKeys("acn2002@live.com");
 
        WebElement pass = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        pass.sendKeys("pass43@21");

        WebElement entrar = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button/span"));
        entrar.click(); 

        WebElement account = driver.findElement(By.xpath("//*[@id=\"header-account-menu-link\"]"));
        account.click(); 

        takeScreenShot();        
        
        WebElement singOut = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/div/a[2]"));
        singOut.click(); 

        takeScreenShot();

    }
   
    @Test
    public void testSetings() {
        driver.get("https://ration.io/");

        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        loginBtn.click();
        
        takeScreenShot();

        WebElement email = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        email.sendKeys("acn2002@live.com");
 
        WebElement pass = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        pass.sendKeys("pass43@21");

        WebElement entrar = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button/span"));
        entrar.click(); 

        WebElement account = driver.findElement(By.xpath("//*[@id=\"header-account-menu-link\"]"));
        account.click(); 

        takeScreenShot();        
        
        WebElement setings = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/div/a[1]"));
        setings.click(); 
        
        WebElement mudarSenha = driver.findElement(By.xpath("//*[@id=\"account-overview\"]/div/div[4]/div[2]/span/a"));
        mudarSenha.click(); 
        
        WebElement passOne = driver.findElement(By.id("password"));
        passOne.sendKeys("pass43@21");
        
        WebElement passTwo = driver.findElement(By.id("confirm-password"));
        passTwo.sendKeys("pass43@21");

        takeScreenShot();        
        
        WebElement saveChanges = driver.findElement(By.xpath("//*[@id=\"change-password\"]/div/form/div[2]/div[2]/div/button"));
        saveChanges.click(); 
        

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
