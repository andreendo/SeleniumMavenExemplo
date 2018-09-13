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
        
        fazLogin();
    }
    
    @After
    public void after() {
        driver.close();
    }

    @Test
    public void testeLogado(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h1")).
                                                                    getText().toLowerCase().startsWith("friends") );
        
        takeScreenShot();
    }

    @Test
    public void testeHelp(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h1")).
                                                                    getText().toLowerCase().startsWith("friends") );
        WebElement botaoHelp = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[3]"));
        botaoHelp.click();
        WebElement campoNome = driver.findElement(By.xpath("//*[@id=\"full-name\"]"));
        campoNome.sendKeys("Teste de Software");
        WebElement campoEmail = driver.findElement(By.xpath("//*[@id=\"email-address\"]"));
        campoEmail.sendKeys("softwareutfpr@gmail.com");
        WebElement campoTopic = driver.findElement(By.xpath("//*[@id=\"topic\"]"));
        campoTopic.sendKeys("Teste com Selenium");
        WebElement campoMessage = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        campoMessage.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a aliquet augue. Suspendisse potenti.");
        
        takeScreenShot();
        
        WebElement botaoSendMessage = driver.findElement(By.xpath("//*[@id=\"contact\"]/div/div/form/div[5]/button"));
        botaoSendMessage.click();
        
        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"contact\"]/div/div/p[1]")).
                                                                    getText().toLowerCase().startsWith("thanks") );
        takeScreenShot();
    }
    
    @Test
    public void testeLougout(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h1")).
                                                                    getText().toLowerCase().startsWith("friends") );
        WebElement botaoAccount = driver.findElement(By.xpath("//*[@id=\"header-account-menu-link\"]"));
        botaoAccount.click();
        WebElement botaoSignOut = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/div/a[2]"));
        botaoSignOut.click();
        
        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"login\"]/div/h1")).
                                                                    getText().toLowerCase().startsWith("sign in") );
        takeScreenShot();
    }
    
    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }
    
    private void fazLogin(){
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
}
