package br.edu.utfpr;

import br.edu.utfpr.po.FindOwnerPage;
import br.edu.utfpr.po.HomePage;
import br.edu.utfpr.po.ListOwnersPage;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author andreendo
 */
public class ListOwnersTest {
    
    private WebDriver driver;
    
    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/home/utfpr/install/selenium/chromedriver");
    }
    
    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);     
    }
    
    @After
    public void after() {
        driver.close();
    }    
    
    @Test
    public void testFindAllOwners() {
        HomePage homePage = new HomePage(driver);
        
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        
        ListOwnersPage listOwnersPage = findOwnerPage.clickFindButton();
        assertEquals("Owners", listOwnersPage.getTitle());
        //check show all
        assertTrue(listOwnersPage.getNumberOfOwners() > 8); 
    }   
    
    @Test
    public void testFindSomeOwners() {
        HomePage homePage = new HomePage(driver);
        
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        
        ListOwnersPage listOwnersPage = 
                findOwnerPage.setLastName("davis")
                .clickFindButton();
        assertEquals("Owners", listOwnersPage.getTitle());

        //check show davis
        assertTrue(listOwnersPage.getNumberOfOwners() >= 2); 
        for(int i = 0; i < listOwnersPage.getNumberOfOwners(); i++) {
            assertTrue(listOwnersPage.getRow(i).toLowerCase().contains("davis"));
        }
    }    
    
    @Test
    public void testFindInexistentOwnerRefactored() {
        HomePage homePage = new HomePage(driver);
        
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        
        findOwnerPage.setLastName("Goodenough")
                .clickFindButton();
        
        assertEquals("has not been found", findOwnerPage.getErrorMessage());
    }        
    
    /**
     * some test as before. Keep it to show the difference.
     */
    @Ignore
    @Test
    public void testFindInexistentOwner() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.xpath("//a[@title='find owners']")).click();
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.clear();
        lastNameField.sendKeys("Goodenough");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        WebElement error = driver.findElement(By.xpath("//*[@id='lastNameGroup']/div/span/div/p"));
        
        assertEquals("has not been found", error.getText());
    }    
}
