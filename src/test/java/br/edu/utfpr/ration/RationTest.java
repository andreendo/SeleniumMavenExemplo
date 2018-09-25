package br.edu.utfpr.ration;


import br.edu.utfpr.exemplomaven.*;
import br.edu.utfpr.po.FaqPage;
import br.edu.utfpr.po.HomePage;
import br.edu.utfpr.po.LoginPage;
import br.edu.utfpr.po.ThingsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
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
//        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");
        
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getTitle().toLowerCase().startsWith("teste") );
//        
//        takeScreenShot();
//        
//        assertTrue(driver.getTitle().startsWith("teste de software"));
//    }
    
//    @Test
//    public void testeLogar() {
//        driver.get("https://ration.io/login");
//        
//        //fill the email adress
//        WebElement email = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input") );
//        email.sendKeys("nakaima.prk@gmail.com");
//        
//        //fill the password
//        WebElement password = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input") );
//        password.sendKeys("teste");
//        
//        //*[@id="login"]/div/div/form/div[4]/button
//        WebElement signIn = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button") );
//        signIn.submit();
//        
//        //*[@id="available-things"]/div/div[1]/h1
//        WebElement h1 = driver.findElement( By.xpath("//*[@id=\"available-things\"]/div/div[1]/h1") );
//        assertEquals("Things", h1.getText().trim());
//        
//    }
    
//    @Test
//    public void logarSenhaErrada() {
//         driver.get("https://ration.io/login");
//        
//        //fill the email adress
//        WebElement email = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input") );
//        email.sendKeys("nakaima.prk@gmail.com");
//        
//        //fill the password
//        WebElement password = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input") );
//        password.sendKeys("senhaErrada");
//        
//        //*[@id="login"]/div/div/form/div[4]/button
//        WebElement signIn = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button") );
//        signIn.submit();
//        
//        //*[@id="login"]/div/div/form/p/small
//        WebElement msgErro = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/p/small") );
//        String expected = "The credentials you entered are not associated with an account. Please check your email and/or password and try again.";
//        assertEquals(expected, msgErro.getText().trim());
//        
//    }
    
    @Test
    public void testeFaqPage() {
        HomePage homePage = new HomePage(driver);
        
        FaqPage faqPage = homePage.getNavBar().goToFaq();
        String expected = "Other than Sails, what technologies, frameworks, or services does this app rely on?";
        assertEquals(expected, faqPage.getFirstH4());
    }
    
    @Test
    public void testeLogar() {
        HomePage homePage = new HomePage(driver);
        
        LoginPage loginPage = homePage.getNavBar().goToLogin();
        loginPage.setEmail("nakaima.prk@gmail.com");
        loginPage.setPassword("teste");
        
        ThingsPage thingsPage = loginPage.submit();
       
        //*[@id="available-things"]/div/div[1]/h1
        assertEquals("Things", thingsPage.getTitle());
    }
    
    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }
}
