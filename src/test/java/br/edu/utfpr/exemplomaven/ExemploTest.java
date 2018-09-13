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
    @Test
    public void testSingUpRation() {
        System.setProperty("webdriver.chrome.driver", "/home/utfpr/install/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ration.io/");
        
        // Button SignUp
        WebElement SignUpButton = driver.findElement( By.xpath("//*[@id=\"page-header\"]/div/div/a") );
        SignUpButton.click();
        
        // Input Full Name
        WebElement FullNameInput = driver.findElement( By.xpath("//*[@id=\"full-name\"]") );
        FullNameInput.sendKeys("Rafael Hideki de Macedo Sampy");

        // Input E-mail Address
        WebElement EmailInput = driver.findElement( By.xpath("//*[@id=\"email-address\"]") );
        EmailInput.sendKeys("rafaelsampy@hotmail.com");

        // Input Choose Password
        WebElement ChoosePasswordInput = driver.findElement( By.xpath("//*[@id=\"password\"]") );
        ChoosePasswordInput.sendKeys("teste1234");

        // Input Confirm Password
        WebElement ConfirmPasswordInput = driver.findElement( By.xpath("//*[@id=\"confirm-password\"]") );
        ConfirmPasswordInput.sendKeys("teste1234");

        // CheckBox Agree Terms
        WebElement AgreeTermsCheckBox = driver.findElement( By.xpath("//*[@id=\"terms-agreement\"]") );
        AgreeTermsCheckBox.isSelected();

        // Button Create Account
        WebElement CreateAccountButton = driver.findElement( By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button/span") );
        CreateAccountButton.click();

    }
    
    @Test
    public void testLogInRation() {
        System.setProperty("webdriver.chrome.driver", "/home/utfpr/install/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ration.io/");
        
        // Button LogIn
        WebElement LogInButton = driver.findElement( By.xpath("//*[@id=\"page-header\"]/div/a[2]") );
        LogInButton.click();
        
        // Input Email Address
        WebElement EmailAddressInput = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input") );
        EmailAddressInput.sendKeys("rafaelsampy@hotmail.com");

        // Input Password
        WebElement InputPasswordInput = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input") );
        InputPasswordInput.sendKeys("teste1234");

        // CheckBox Remenber Me
        WebElement RemenberMeCheckBox = driver.findElement( By.xpath("//*[@id=\"remember\"]") );
        RemenberMeCheckBox.isSelected();
        
        // Button Sign In
        WebElement SignInButton = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button") );
        SignInButton.click();
    }
}
