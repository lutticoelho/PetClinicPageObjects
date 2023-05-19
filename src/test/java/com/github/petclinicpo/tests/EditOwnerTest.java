package com.github.petclinicpo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.github.petclinicpo.po.EditOwnerPage;
import com.github.petclinicpo.po.HomePage;
import com.github.petclinicpo.po.OwnerInformationPage;

/**
 *
 * @author andreendo
 */
public class EditOwnerTest {
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
    public void testEditFirstOwnerListed() {
        HomePage homePage = new HomePage(driver);
        
        EditOwnerPage editOwnerPage = homePage.getMenu().goToFindOwners()
                .clickFindButton()
                .selectOwner(1)
                .clickEditButton();
        String oName = editOwnerPage.getFirstName();
        assertNotEquals("", oName);
        String oLastname = editOwnerPage.getLastName();
        assertNotEquals("", oLastname);
        String oAddress = editOwnerPage.getAddress();
        assertNotEquals("", oAddress);
        String oCity = editOwnerPage.getCity();
        assertNotEquals("", oCity);
        String oPhone = editOwnerPage.getTelephone();
        assertNotEquals("", oPhone);
         
        editOwnerPage.setFirstName(oName + "a");
        editOwnerPage.setLastName(oLastname + "i");
        editOwnerPage.setAddress(oAddress + "p");
        editOwnerPage.setCity(oCity + "o");
        editOwnerPage.setTelephone(oPhone.substring(1) + "1");
        OwnerInformationPage ownerInfoPage = editOwnerPage.addValidData();
        
        assertEquals("Owner Information", ownerInfoPage.getTitle());
        assertEquals(oName + "a " + oLastname + "i", ownerInfoPage.getName());
        assertEquals(oAddress + "p", ownerInfoPage.getAddress());
        assertEquals(oCity + "o", ownerInfoPage.getCity());
        assertEquals(oPhone.substring(1) + "1", ownerInfoPage.getTelephone());        
    }
}
