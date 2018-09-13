
package br.edu.utfpr.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FriendsPage extends LoginPage{
    
    WebElement botaoHelp;
    WebElement campoNome;
    WebElement campoEmail;
    
            
    public FriendsPage(WebDriver driver) {
        super(driver);
    }
    
}
