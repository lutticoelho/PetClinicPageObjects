package br.edu.utfpr;

import br.edu.utfpr.po.ErrorPage;
import br.edu.utfpr.po.FindOwnerPage;
import br.edu.utfpr.po.HomePage;
import br.edu.utfpr.po.ListOwnersPage;
import br.edu.utfpr.po.VeterinariansPage;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        driver = new ChromeDriver();
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
    public void testFindNoOwner() {
        HomePage homePage = new HomePage(driver);
        
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        
        findOwnerPage
                .setLastName("XXX YYYY")
                .clickFindButton();
        
        assertEquals("has not been found", findOwnerPage.getErrorMessage());
    }        
}
