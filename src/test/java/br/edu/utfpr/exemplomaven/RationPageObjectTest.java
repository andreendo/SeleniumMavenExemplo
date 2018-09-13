package br.edu.utfpr.exemplomaven;

import br.edu.utfpr.exemplomaven.po.loggedIn.FriendsPage;
import br.edu.utfpr.exemplomaven.po.loggedIn.HelpPage;
import br.edu.utfpr.exemplomaven.po.loggedIn.ThingsPage;
import br.edu.utfpr.exemplomaven.po.loggedOut.FaqPage;
import br.edu.utfpr.exemplomaven.po.loggedOut.HomePage;
import br.edu.utfpr.exemplomaven.po.loggedOut.LoginPage;
import br.edu.utfpr.exemplomaven.po.loggedOut.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RationPageObjectTest {

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
    public void navigation(){
        HomePage homePage = new HomePage(driver);

        FaqPage faqPage = homePage.getMenu().goToFaqPage();
        assertEquals("FAQ", faqPage.getTitle());

        LoginPage loginPage = faqPage.getMenu().goToLoginPage();
        assertEquals("Sign in to your account", loginPage.getTitle());

        SignUpPage signUpPage = loginPage.getMenu().goToSignUpPage();
        assertEquals("Create an account", signUpPage.getTitle());

        homePage = signUpPage.getMenu().goToHomePage();
        assertEquals("Be cool. Share your stuff.", homePage.getTitle());
    }

    @Test
    public void wrongLogin(){
        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.getMenu().goToLoginPage();

        loginPage.setEmail("rafaelnsantos1@gmail.com")
                .setPassword("wrongpassword")
                .submitInvalidCredentials();

        assertEquals("The credentials you entered are not associated with an account. Please check your email and/or password and try again.", loginPage.getErrorMessage());
    }

    @Test
    public void login(){
        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.getMenu().goToLoginPage();

        FriendsPage friendsPage = loginPage.setEmail("rafaelnsantos1@gmail.com")
                .setPassword("mypassword")
                .submitValidCredentials();

        assertEquals("Friends", friendsPage.getTitle());
    }

    @Test
    public void navigationLoggedIn(){
        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.getMenu().goToLoginPage();

        FriendsPage friendsPage = loginPage.setEmail("rafaelnsantos1@gmail.com")
                .setPassword("mypassword")
                .submitValidCredentials();

        ThingsPage thingsPage = friendsPage.getMenu().goToThingsPage();
        assertEquals("Things", thingsPage.getTitle());

        HelpPage helpPage = thingsPage.getMenu().goToHelpPage();
        assertEquals("Get in touch", helpPage.getTitle());

        friendsPage = helpPage.getMenu().goToHomePage();
        assertEquals("Friends", friendsPage.getTitle());

    }

    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }
}

