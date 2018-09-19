package br.edu.utfpr.exemplomaven;


import br.edu.utfpr.exemplomaven.po.FriendsPage;
import br.edu.utfpr.exemplomaven.po.LoginPage;
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
import org.junit.Ignore;
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
    
    @Test @Ignore
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
    
    @Test @Ignore
    public void doLogin(){
        driver.get("https://ration.io/login");
        
        WebElement email = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        email.sendKeys("caio_jahu@hotmail.com");
        
        WebElement password = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        password.sendKeys("gzwqpl80");
        
        WebElement signIn = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));
        signIn.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().equals("https://ration.io/friends")); 
        
        assertEquals("https://ration.io/friends", driver.getCurrentUrl());
    }
    
    @Test  //@Ignore
    public void doLoginPO(){
        LoginPage lg = new LoginPage(driver);
        
        FriendsPage fp = lg.preencherEmail("caio@teste.com")
                        .preencherPassword("teste123")
                        .clicarBotaoSignIn()
                        .esperarCarregar();
        
        assertEquals("Invite friends", fp.getBotaoInvite());
    }
    
    
    @Test @Ignore
    public void inviteFriend(){
        doLogin();
        
        WebElement buttonInviteFriends = driver.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/div/button"));
        buttonInviteFriends.click();
        
        WebElement inputNome = driver.findElement(By.xpath("//*[@id=\"friends\"]/div[2]/div[2]/div/form/div[1]/div[1]/div/div[1]/input"));
        inputNome.sendKeys("amigo legal");
        
        WebElement inputEmail = driver.findElement(By.xpath("//*[@id=\"friends\"]/div[2]/div[2]/div/form/div[1]/div[1]/div/div[2]/input"));
        inputEmail.sendKeys("amigolegal@gmail.com");
        
        WebElement buttonEnviar = driver.findElement(By.xpath("//*[@id=\"friends\"]/div[2]/div[2]/div/form/div[2]/button[1]"));
        buttonEnviar.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.getCurrentUrl().equals("https://ration.io/friends"));
    }
    
    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }
}
