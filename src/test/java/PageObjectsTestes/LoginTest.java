package PageObjectsTestes;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import pageObjects.FriendsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RationPage;

/**
 *
 * @author CINTIA
 */
public class LoginTest {

    private WebDriver driver;
    HomePage homePage;
    RationPage rationPage;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void Before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//tenta pesquisar novamente
        driver.get("https://ration.io/");

        homePage = new HomePage(driver);
        rationPage = new RationPage(driver);
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void realizarLogin() {
        String tEmail = "zxswglbm@getairmail.com";
        String tPass = "teste123";
        LoginPage login = (LoginPage) homePage.selecionaItemMenu().IrParaPaginaLogin()
                .realizarLogin(tEmail, tPass);
        assertEquals("Friends", login.IrParaFriendsPage().VerificaTitulo());

    }

    @Test
    public void LoginIvalido() {
        String tEmail = "zxswglbm@getairmail.com";
        String tPass = "teste";
        LoginPage login = (LoginPage) homePage.selecionaItemMenu().IrParaPaginaLogin()
                .realizarLogin(tEmail, tPass);
        assertTrue(login.Error());
    }

}
