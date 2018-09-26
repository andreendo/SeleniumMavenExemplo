package br.edu.utfpr.exemplomaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;


/**
 *
 * @author CINTIA
 */
public class RationTest {

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

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//tenta pesquisar novamente
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void testaLoginSemCadastro() {
        driver.get("https://ration.io/");

        WebElement clickLogin = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        clickLogin.click();

        takeScreenShot();

        WebElement login = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        login.sendKeys("testelogin@login.com.br");

        WebElement pass = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        pass.sendKeys("teste123");

        WebElement assinar = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));
        assinar.submit();

        WebElement msgErro = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/p/small"));
        assertEquals("The credentials you entered are not associated with an account. "
                + "Please check your email and/or password and try again.", msgErro.getText().trim());

        takeScreenShot();
    }
    
    @Ignore
    @Test
    public void testaRealizarCadastro() {
        driver.get("https://ration.io/");

        WebElement clickSignUp = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/a"));
        clickSignUp.click();

        takeScreenShot();

        WebElement fullName = driver.findElement(By.id("full-name"));
        fullName.sendKeys("Teste002");

        WebElement email = driver.findElement(By.id("email-address"));
        email.sendKeys("zxswglbm@getairmail.com");
        
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("teste123");
        
        WebElement confPass = driver.findElement(By.id("confirm-password"));
        confPass.sendKeys("teste123");
        
        WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"terms-agreement\"]"));
        checkBox.click();

        WebElement button = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button"));
        button.submit();
        
        
        WebElement titulo = driver.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h1"));
        assertEquals("Friends", titulo.getText());
        

        takeScreenShot();
    }

    @Test
    public void testaLoginSemSenha() {
        driver.get("https://ration.io/");
       

        WebElement clickLogin = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        clickLogin.click();

        takeScreenShot();
        
        WebElement login = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        login.sendKeys("testelogin@login.com.br");

        WebElement assinar = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));
        assinar.submit();

        WebElement msgErro = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/div"));
        assertEquals("Please enter your password.", msgErro.getText().trim());

        takeScreenShot();
    }

    
    @Test
    public void testaLoginSValido() {
        driver.get("https://ration.io/");

        WebElement clickLogin = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        clickLogin.click();

        takeScreenShot();

        WebElement login = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        login.sendKeys("zxswglbm@getairmail.com");

        WebElement pass = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        pass.sendKeys("teste123");

        WebElement assinar = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));
        assinar.submit();

        WebElement titulo = driver.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h1"));
        assertEquals("Friends", titulo.getText());

        takeScreenShot();
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
