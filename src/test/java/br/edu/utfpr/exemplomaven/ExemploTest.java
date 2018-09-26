package br.edu.utfpr.exemplomaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.utility.RandomString;
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

    private static String emailMemory;
    private static String passwordMemory;

    WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");;
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    @After
    public void after() {
        driver.close();
    }

    @Ignore
    public void testGoogleSearch() {
        driver.get("https://www.google.com.br/");
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("teste de software");

        takeScreenShot();

        searchInput.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.getTitle().toLowerCase().startsWith("teste"));

        takeScreenShot();

        assertTrue(driver.getTitle().startsWith("teste de software"));
    }

    @Test
    public void testLoginSenhaVazios() {
        driver.get("https://ration.io/");

        WebElement linkLogin = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        takeScreenShot();

        linkLogin.click();

        WebElement inputEmail = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        WebElement inputPassword = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        WebElement buttonLogin = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));

        inputEmail.sendKeys("");
        inputPassword.sendKeys("");
        buttonLogin.click();

        List<WebElement> invalidFeedback = driver.findElements(By.className("invalid-feedback"));

        takeScreenShot();
        assertEquals(2, invalidFeedback.size());
    }

    @Test
    public void testLoginInvalido() {
        driver.get("https://ration.io/");

        WebElement linkLogin = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        takeScreenShot();

        linkLogin.click();

        WebElement inputEmail = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        WebElement inputPassword = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        WebElement buttonLogin = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));

        inputEmail.sendKeys("joaopaulopaez@gmail.com");
        inputPassword.sendKeys("joaopaulopaez@gmail.com");
        buttonLogin.click();

        List<WebElement> invalidFeedback = driver.findElements(By.className("text-danger"));

        takeScreenShot();
        assertEquals(1, invalidFeedback.size());
    }

    @Test
    public void testSignUpWithoutAgreeTerms() throws InterruptedException {
        driver.get("https://ration.io/");

        WebElement linkSignUp = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/a"));
        takeScreenShot();

        linkSignUp.click();

        WebElement inputFullName = driver.findElement(By.xpath("//*[@id=\"full-name\"]"));
        WebElement inputEmail = driver.findElement(By.xpath("//*[@id=\"email-address\"]"));
        WebElement inputPassword = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement inputConfimPassword = driver.findElement(By.xpath("//*[@id=\"confirm-password\"]"));
        WebElement buttonSignUp = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button/span"));

        String random = RandomString.make(15);
        String name = "João Paulo Paes " + random;
        String email = random + "@gmail.com";

        System.out.println(email);

        this.emailMemory = email;
        this.passwordMemory = "joaopaulopaez@gmail.com";

        inputFullName.sendKeys(name);
        inputEmail.sendKeys(email);
        inputPassword.sendKeys("joaopaulopaez@gmail.com");
        inputConfimPassword.sendKeys("joaopaulopaez@gmail.com");
        takeScreenShot();

        buttonSignUp.click();

        WebElement labelCheckboxError = driver.findElement(By.cssSelector(".form-check-label.text-danger"));

        takeScreenShot();
        labelCheckboxError.click();

        //as duas instruções abaixo servem para aguardar o efeito do botão terminar
        inputFullName.sendKeys("");
        inputEmail.sendKeys("");

        takeScreenShot();
        buttonSignUp.click();
        takeScreenShot();

        System.out.println(driver.getCurrentUrl());

        Thread.sleep((long) 2000);
        assertEquals(Boolean.TRUE, driver.getCurrentUrl().contains("friend"));
    }

    @Test
    public void testLogin() throws InterruptedException {
        driver.get("https://ration.io/");

        WebElement linkLogin = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[2]"));
        takeScreenShot();

        linkLogin.click();

        WebElement inputEmail = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        WebElement inputPassword = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        WebElement buttonLogin = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button"));

        inputEmail.sendKeys(emailMemory);
        inputPassword.sendKeys(passwordMemory);
        buttonLogin.click();
       
        Thread.sleep(2000);
        takeScreenShot();
        
        System.out.println(driver.toString());

        assertEquals(Boolean.TRUE, driver.getCurrentUrl().contains("friend"));
    }

    @Ignore
    public void testLogout() {
        WebElement dropdownAccount = driver.findElement(By.xpath("//*[@id=\"header-account-menu-link\"]"));
        dropdownAccount.click();
        WebElement buttonLogout = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/div/div/a[2]"));
        buttonLogout.click();
        assertEquals(Boolean.TRUE, driver.getCurrentUrl().contains("login"));
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
