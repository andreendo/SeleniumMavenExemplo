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
    
    WebDriver driver;
    
    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
     
    }
    
    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized"); 
        
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://ration.io/");
        
        // Login 
        // Button - LogIn
        WebElement LogInButton = driver.findElement( By.xpath("//*[@id=\"page-header\"]/div/a[2]") );
        LogInButton.click();
        
        // Input - E-mail Address
        WebElement EmailAddressInput = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input") );
        EmailAddressInput.sendKeys("rafaelsampy@hotmail.com");

        // Input - Password
        WebElement PasswordInput = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input") );
        PasswordInput.sendKeys("teste1234");
        
        // Button - Sign In
        WebElement SignInButton = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button") );
        SignInButton.click();
              
        // Message - System
        WebElement SystemMessage = driver.findElement( By.xpath("//*[@id=\"friends\"]/div/div[1]/h2") );
        assertEquals("View and add people to share stuff with.", SystemMessage.getText());
    }
    
    @After
    public void after() {
        driver.close();
    }
    
    /* Usuario já Cadastrado */
    @Ignore
    @Test
    public void testSingUpRation() {   
        //  Button - SignUp
        WebElement SignUpButton = driver.findElement( By.xpath("//*[@id=\"page-header\"]/div/div/a") );
        SignUpButton.click();
        
        // Input - Full Name
        WebElement FullNameInput = driver.findElement( By.xpath("//*[@id=\"full-name\"]") );
        FullNameInput.sendKeys("Rafael Hideki de Macedo Sampy");

        // Input - E-mail Address
        WebElement EmailInput = driver.findElement( By.xpath("//*[@id=\"email-address\"]") );
        EmailInput.sendKeys("rafaelsampy@hotmail.com");

        // Input - Choose Password
        WebElement ChoosePasswordInput = driver.findElement( By.xpath("//*[@id=\"password\"]") );
        ChoosePasswordInput.sendKeys("teste1234");

        // Input - Confirm Password
        WebElement ConfirmPasswordInput = driver.findElement( By.xpath("//*[@id=\"confirm-password\"]") );
        ConfirmPasswordInput.sendKeys("teste1234");

        // CheckBox - Agree Terms
        WebElement AgreeTermsCheckBox = driver.findElement( By.xpath("//*[@id=\"terms-agreement\"]") );
        AgreeTermsCheckBox.isSelected();

        // Button - Create Account
        WebElement CreateAccountButton = driver.findElement( By.xpath("//*[@id=\"signup\"]/div/div/form/div[6]/button/span") );
        CreateAccountButton.click();
        
        // Message - System
        WebElement SystemMessage = driver.findElement( By.xpath("//*[@id=\"friends\"]/div/div[1]/h2") );
        assertEquals("View and add people to share stuff with.", SystemMessage.getText());
    }
    
    /* Login através do Before */
    @Ignore
    @Test
    public void testLogInRation() {  
        // Button - LogIn
        WebElement LogInButton = driver.findElement( By.xpath("//*[@id=\"page-header\"]/div/a[2]") );
        LogInButton.click();
        
        // Input - E-mail Address
        WebElement EmailAddressInput = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input") );
        EmailAddressInput.sendKeys("rafaelsampy@hotmail.com");

        // Input - Password
        WebElement PasswordInput = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input") );
        PasswordInput.sendKeys("teste1234");

        // CheckBox - Remenber Me
        WebElement RemenberMeCheckBox = driver.findElement( By.xpath("//*[@id=\"remember\"]") );
        RemenberMeCheckBox.isSelected();
        
        // Button - Sign In
        WebElement SignInButton = driver.findElement( By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button") );
        SignInButton.click();
              
        // Message - System
        WebElement SystemMessage = driver.findElement( By.xpath("//*[@id=\"friends\"]/div/div[1]/h2") );
        assertEquals("View and add people to share stuff with.", SystemMessage.getText());
    }
    
    @Test
    public void testFriends() { 
        // Button - Invite Friends
        WebElement InviteFriendsButton = driver.findElement( By.xpath("//*[@id=\"friends\"]/div/div[1]/div/button") );
        InviteFriendsButton.click();
        
        // Input - Name
        WebElement NameInput = driver.findElement( By.xpath("//*[@id=\"friends\"]/div[2]/div[2]/div/form/div[1]/div[1]/div/div[1]/input") );
        NameInput.sendKeys("Henrique");

        // Input - E-mail Address
        WebElement EmailAddressInput2 = driver.findElement( By.xpath("//*[@id=\"friends\"]/div[2]/div[2]/div/form/div[1]/div[1]/div/div[2]/input") );
        EmailAddressInput2.sendKeys("henrique.nicolli@gmail.com");
        
        // Button - ADD
        WebElement AddButton = driver.findElement( By.xpath("//*[@id=\"friends\"]/div[2]/div[2]/div/form/div[2]/button[1]/span") );
        AddButton.click();
              
        // Message - Grid Friend Request
        WebElement GridFriendRequestMessage = driver.findElement( By.xpath("//*[@id=\"friends\"]/div/div[2]/table/tbody/tr/td[1]/strong") );
        assertEquals("henrique", GridFriendRequestMessage.getText());
    }
    
    @Test
    public void testHelp() { 
        // Button - Help
        WebElement HelpButton = driver.findElement( By.xpath("//*[@id=\"page-header\"]/div/a[3]") );
        HelpButton.click();
        
        // Input - Name
        WebElement NameInput = driver.findElement( By.xpath("//*[@id=\"full-name\"]") );
        NameInput.sendKeys("Rafael Hideki de Macedo Sampy");

        // Input - E-mail Address
        WebElement EmailAddressInput2 = driver.findElement( By.xpath("//*[@id=\"email-address\"]") );
        EmailAddressInput2.sendKeys("rafaelsampy@hotmail.com");

        // Input - Topic
        WebElement TopicInput = driver.findElement( By.xpath("//*[@id=\"topic\"]") );
        TopicInput.sendKeys("Test Topic");

        // TextArea - Message
        WebElement MessageTextArea = driver.findElement( By.xpath("//*[@id=\"message\"]") );
        MessageTextArea.sendKeys("Test Message");
        
        // Button - Send Message
        WebElement SendMessageButton = driver.findElement( By.xpath("//*[@id=\"contact\"]/div/div/form/div[5]/button") );
        SendMessageButton.click();
              
        // Message - Feedback
        WebElement FeedbackMessage = driver.findElement( By.xpath("//*[@id=\"contact\"]/div/div/p[2]") );
        assertEquals("We have received your message, and someone from our team will get back to you soon.", FeedbackMessage.getText());
    }
    
    @Test
    public void testSingOut() { 
        // Button - Account
        WebElement AccountButton = driver.findElement( By.xpath("//*[@id=\"header-account-menu-link\"]") );
        AccountButton.click();
        
        // Button - Sing Out
        WebElement SingOutButton = driver.findElement( By.xpath("//*[@id=\"page-header\"]/div/div/div/a[2]") );
        SingOutButton.click();
        
        // Message - System
        WebElement SystemMessage = driver.findElement( By.xpath("//*[@id=\"login\"]/div/h1") );
        assertEquals("Sign in to your account", SystemMessage.getText());
    }
}
