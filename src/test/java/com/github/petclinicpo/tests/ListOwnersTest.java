package com.github.petclinicpo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.github.petclinicpo.po.FindOwnerPage;
import com.github.petclinicpo.po.HomePage;
import com.github.petclinicpo.po.ListOwnersPage;

/**
 *
 * @author andreendo
 */
public class ListOwnersTest {
    
    private WebDriver driver;
    
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
        assertEquals(5, listOwnersPage.getNumberOfOwners()); 
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
    public void testFindInexistentOwner() {
        HomePage homePage = new HomePage(driver);
        
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        
        findOwnerPage.setLastName("Goodenough")
                .clickFindButton();
        
        assertEquals("has not been found", findOwnerPage.getErrorMessage());
    }        
    
}
