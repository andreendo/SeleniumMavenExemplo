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
    private static String CHROMEDRIVER_LOCATION = "/Users/fabriciojso/utfpr/topicos-avancados-em-teste/jars/chromedriver";
    
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
    public void testAcessaFAQ() {
        driver.get("https://ration.io/");
        WebElement faqLink = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[1]"));
        faqLink.click();
        
        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("FAQ", h1.getText());
    }
    
    @Test
    public void testAcessaPaginaDeCriarConta() {
        driver.get("https://ration.io/");
        WebElement joinLink = driver.findElement(By.xpath("//*[@id=\"homepage\"]/div[2]/div/div[2]/a"));
        joinLink.click();
        
        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("Create an account", h1.getText());
    }
    
    @Test
    public void testValidacaoCampos() {
        driver.get("https://ration.io/signup");
        WebElement buttonSubmit = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button"));
        buttonSubmit.click();
        
        WebElement erro1 = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[1]/div"));
        assertEquals("Please enter your full name.", erro1.getText());
        
        WebElement erro2 = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[2]/div"));
        assertEquals("Please enter a valid email address.", erro2.getText());
        
        WebElement erro3 = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[3]/div"));
        assertEquals("Please enter a password.", erro3.getText());
    }
    
    @Test
    public void testValidacaoSenhasDiferentes() {
        driver.get("https://ration.io/signup");
        WebElement nome = driver.findElement(By.xpath("//*[@id=\"full-name\"]"));
        WebElement email = driver.findElement(By.xpath("//*[@id=\"email-address\"]"));
        WebElement senha = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement confirmaSenha = driver.findElement(By.xpath("//*[@id=\"confirm-password\"]"));
        WebElement buttonSubmit = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button"));
        
        nome.sendKeys("Fabricio");
        email.sendKeys("fabricio.jhonata@gmail.com");
        senha.sendKeys("123");
        confirmaSenha.sendKeys("321");
        buttonSubmit.click();
        
        WebElement mensagemErro = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[4]/div"));
        assertEquals("Your password and confirmation do not match.", mensagemErro.getText());
    }
    
    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }
}
