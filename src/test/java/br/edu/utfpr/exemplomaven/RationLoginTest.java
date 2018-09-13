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

public class RationLoginTest {

    private static int scId = 0;

    WebDriver driver;
    WebDriverWait wait;

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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void testRationLogin() {
        login();
        assertTrue(driver.findElement(By.xpath("/html/body/div/div/div/div[1]/h1")).getText().toLowerCase().startsWith("friends") );
        takeScreenShot();
    }

    @Test
    public void testRationWrongLogin() {
        driver.get("https://ration.io");
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/header/div/a[2]"));
        takeScreenShot();
        loginButton.click();

        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body/div/div/div/h1")).getText().toLowerCase().startsWith("sign") );
        takeScreenShot();

        WebElement emailAddress = driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[1]/input"));
        WebElement password = driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[2]/input"));

        emailAddress.sendKeys("rafaelnsantos1@gmail.com");
        password.sendKeys("wrongpassword");
        takeScreenShot();

        password.submit();

        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body/div/div/div/div/form/p/small")).getText().toLowerCase().startsWith("the") );

        assertTrue(driver.findElement(By.xpath("/html/body/div/div/div/div/form/p/small")).getText().toLowerCase().startsWith("the credentials you entered are not associated with an account. please check your email and/or password and try again.") );
        takeScreenShot();
    }

    @Test
    public void inviteFriend(){
        login();
        takeScreenShot();

        WebElement inviteFriendsButton = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/button"));

        inviteFriendsButton.click();

        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/h5")).getText().toLowerCase().startsWith("invite"));

        assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/h5")).getText().toLowerCase().startsWith("invite friends"));
        takeScreenShot();
    }

    @Test
    public void sendFile(){
        login();
        takeScreenShot();

        WebElement thingsLink = driver.findElement(By.xpath("/html/body/div/header/div/a[2]"));

        thingsLink.click();

        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body/div/div/div/div[1]/h1")).getText().toLowerCase().startsWith("things"));

        assertTrue(driver.findElement(By.xpath("/html/body/div/div/div/div[1]/h1")).getText().toLowerCase().startsWith("things"));
        takeScreenShot();

        WebElement addItemButton = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/button"));

        addItemButton.click();

        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/h5")).getText().toLowerCase().startsWith("upload"));

        assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/h5")).getText().toLowerCase().startsWith("upload a new thing"));
        takeScreenShot();

        WebElement imagePath = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/form/div[1]/div/div/div/div[2]/span/input"));

        imagePath.sendKeys("/home/r/galaxy.jpg");
        takeScreenShot();

        WebElement name = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/form/div[1]/div[2]/input"));

        name.sendKeys("galaxy");
        takeScreenShot();

        WebElement sendButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/form/div[2]/button[1]"));

        sendButton.click();

        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body/div/div/div/div[2]/div[1]/span")).getText().toLowerCase().startsWith("galaxy"));

        assertTrue(driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[1]/span")).getText().toLowerCase().startsWith("galaxy"));
        takeScreenShot();
    }

    private void login () {
        driver.get("https://ration.io");
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/header/div/a[2]"));
        takeScreenShot();
        loginButton.click();

        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body/div/div/div/h1")).getText().toLowerCase().startsWith("sign") );
        takeScreenShot();

        WebElement emailAddress = driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[1]/input"));
        WebElement password = driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[2]/input"));

        emailAddress.sendKeys("rafaelnsantos1@gmail.com");
        password.sendKeys("mypassword");
        takeScreenShot();

        password.submit();

        wait.until( (ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("/html/body/div/div/div/div[1]/h1")).getText().toLowerCase().startsWith("friends") );
    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }
}

