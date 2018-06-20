package br.edu.utfpr.exemplomaven;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
 */
public class RationTest {

    private static String CHROMEDRIVER_LOCATION = "./chromedriver.exe";

    private static int scId = 0;

    WebDriver driver;
    WebDriver driverGitHub;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
    }

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        // Não realizada o download com a opção headless
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void after() {
        driver.close();
    }

    // Verifico se existem 8 perguntas na página de FAQ do site
    @Test
    public void testRatioFAQ() {
        driver.get("https://ration.io/");
        WebElement link = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div/a[1]"));

        link.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"faq\"]/div/h1")));

        int questCount = driver.findElements(By.className("q-and-a")).size();

        assertEquals(questCount, 8);
    }

    // Verifico se é possível realizar o download do projeto open source
    @Test
    public void testRatioDownload() {
        driver.get("https://ration.io/");
        WebElement buttonDownload = driver.findElement(By.xpath("//*[@id=\"homepage\"]/div[3]/div/div/a[2]"));

        int sizeFolderBefore = (new File("C:/Users/Henrique/Downloads").listFiles().length);

        buttonDownload.click();

        // Para driver visualizar os elementos da página que abriu
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.navigate().refresh();

        driver.findElement(By.tagName("summary")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.partialLinkText("Download")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        int sizeFolderAfter = (new File("C:/Users/Henrique/Downloads").listFiles().length);

        assertTrue(sizeFolderBefore < sizeFolderAfter);
    }

    // Validando as mensagens de erro do formulário de Contato
    @Test
    public void testRatioContactForm() {
        driver.get("https://ration.io/");
        WebElement buttonContactUs = driver.findElement(By.partialLinkText("Contact"));

        buttonContactUs.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement sendForm = driver.findElement(By.xpath("//*[@id=\"contact\"]/div/div/form/div[5]/button"));
        sendForm.click();
        assertEquals("Please let us know what to call you.", driver.findElement(By.xpath("//*[@id=\"contact\"]/div/div/form/div[1]/div")).getText());
        driver.findElement(By.id("full-name")).sendKeys("Teste");

        sendForm.click();
        assertEquals("Please enter a valid email address.", driver.findElement(By.xpath("//*[@id=\"contact\"]/div/div/form/div[2]/div")).getText());
        driver.findElement(By.name("email-address")).sendKeys("test@gmail.com");

        sendForm.click();
        assertEquals("Please choose a topic for your message.", driver.findElement(By.xpath("//*[@id=\"contact\"]/div/div/form/div[3]/div")).getText());
        driver.findElement(By.name("topic")).sendKeys("Teste");
    }

}
