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
public class ExemploTest {

    /**
     * Vc precisa identificar onde estah o chromedriver. Baixar de:
     * https://sites.google.com/a/chromium.org/chromedriver/downloads
     *
     * Versão utilizada do chromedriver: 2.35.528139
     */
    private static String CHROMEDRIVER_LOCATION = "C:\\Users\\Edmundo\\Documents\\MESTRADO\\TESTE\\TESTE DE SISTEMA\\9 - CHROME DRIVER\\chromedriver.EXE";
    
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
    public void testLoginESenhaErrado() {
        driver.get("https://ration.io");
        WebElement campoEntrar = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        campoEntrar.click();
        
        WebElement inputEmail = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        inputEmail.sendKeys("edmundo1100@hotmail.com");
        WebElement inputSenha = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        inputSenha.sendKeys("123");
        
        WebElement botaoAssinar = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));
        botaoAssinar.submit();
        
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement mensagemErro = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/p/small"));
        String erro = mensagemErro.getText();
        
        takeScreenShot();
 
        assertEquals("The credentials you entered are not associated with an account. Please check your email and/or password and try again.",erro);
    }
    
    @Test
    public void testValidacoesCamposInscricao() {
        driver.get("https://ration.io");
        WebElement campoSignUp = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/a"));
        campoSignUp.click();
        
        WebElement botaoCreateAccount = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button"));
        botaoCreateAccount.click();
        
        WebDriverWait esperar = new WebDriverWait(driver, 5);
        takeScreenShot();
        
        WebElement feedBackNome = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[1]/div"));
        WebElement feedBackEmail = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[2]/div"));
        WebElement feedBackSenha = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[3]/div"));
        
        String erroNome = feedBackNome.getText();
        String erroEmail = feedBackEmail.getText();
        String erroSenha = feedBackSenha.getText();
        
        assertEquals("Please enter your full name.",erroNome);
        assertEquals("Please enter a valid email address.",erroEmail);
        assertEquals("Please enter a password.",erroSenha);
    }
    
    @Test
    public void testValidacooSenhaDiferentes() {
        driver.get("https://ration.io");
        WebElement campoSignUp = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/a"));
        campoSignUp.click();
        
        WebElement inputSenha = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        inputSenha.sendKeys("123");
        WebElement inputConfirmacaoSenha = driver.findElement(By.xpath("//*[@id=\"confirm-password\"]"));
        inputConfirmacaoSenha.sendKeys("1233");
        
        WebElement botaoCreateAccount = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button"));
        botaoCreateAccount.click();
        
        WebDriverWait esperar = new WebDriverWait(driver, 5);
        takeScreenShot();
        
        WebElement feedBackConfirmacaoSenha = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[4]/div"));
 
        String erroSenhaDiferentes = feedBackConfirmacaoSenha.getText();
        
        assertEquals("Your password and confirmation do not match.",erroSenhaDiferentes);
    }
    
    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }
}
