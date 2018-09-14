package br.edu.utfpr.exemplomaven.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Setup {

    public static WebDriver setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");

        WebDriver webdriver = new ChromeDriver(chromeOptions);

        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return webdriver;
    }
    
}
