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

import com.github.petclinicpo.po.EditOwnerPage;
import com.github.petclinicpo.po.FindOwnerPage;
import com.github.petclinicpo.po.HomePage;
import com.github.petclinicpo.po.OwnerInformationPage;

/**
 *
 * @author andreendo
 */
public class AddOwnerTest {
    
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
        chromeOptions.addArguments("lang=en-US");
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);     
    }
    
    @After
    public void after() {
        driver.close();
    }    
    
    @Test
    public void testSuccessfulOwnerInsertion() {
        HomePage homePage = new HomePage(driver);
        
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        EditOwnerPage editOwnerPage = findOwnerPage.goToAddOwner();
        assertEquals("Owner", editOwnerPage.getTitle());
        
        OwnerInformationPage ownerInfoPage = editOwnerPage.setFirstName("Joao")
                .setLastName("Da Silva")
                .setAddress("Rua das Acacias, 451")
                .setCity("Sao Carlos")
                .setTelephone("993335544")
                .addValidData();
        
        assertEquals("Owner Information", ownerInfoPage.getTitle());
        assertEquals("Joao Da Silva", ownerInfoPage.getName());
        assertEquals("Rua das Acacias, 451", ownerInfoPage.getAddress());
        assertEquals("Sao Carlos", ownerInfoPage.getCity());
        assertEquals("993335544", ownerInfoPage.getTelephone());
    }    
    
    @Test
    public void testErrorNoLastNameOwnerInsertion() {
        HomePage homePage = new HomePage(driver);
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        EditOwnerPage editOwnerPage = findOwnerPage.goToAddOwner();
        editOwnerPage.setFirstName("Jose")
                .setAddress("Rua das Acacias, 451")
                .setCity("Sao Carlos")
                .setTelephone("993335544")
                .addInvalidData();
        
        assertEquals(1, editOwnerPage.getNumberOfErrors());
        assertTrue(editOwnerPage.getErrorMessage(0).endsWith("must not be empty"));
    }
    
    @Test
    public void testErrorNoData() {
        HomePage homePage = new HomePage(driver);
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        EditOwnerPage editOwnerPage = findOwnerPage.goToAddOwner();
        editOwnerPage.addInvalidData();
        
        assertEquals(5, editOwnerPage.getNumberOfErrors());
    }    
}
