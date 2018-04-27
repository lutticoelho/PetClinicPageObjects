package br.edu.utfpr;

import br.edu.utfpr.po.AddOwnerPage;
import br.edu.utfpr.po.FindOwnerPage;
import br.edu.utfpr.po.HomePage;
import br.edu.utfpr.po.OwnerInformationPage;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author andreendo
 */
public class AddOwnerTest {
    
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
    public void testSuccessfulOwnerInsertion() {
        HomePage homePage = new HomePage(driver);
        
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        AddOwnerPage addOwnerPage = findOwnerPage.goToAddOwner();
        assertEquals("Owner", addOwnerPage.getTitle());
        
        OwnerInformationPage ownerInfoPage = addOwnerPage.setFirstName("Joao")
                .setLastName("Da Silva")
                .setAddress("Rua das Acacias, 451")
                .setCity("Cornelio Procopio")
                .setTelephone("993335544")
                .addValidData();
        
        assertEquals("Owner Information", ownerInfoPage.getTitle());
        assertEquals("Joao Da Silva", ownerInfoPage.getName());
        assertEquals("Rua das Acacias, 451", ownerInfoPage.getAddress());
        assertEquals("Cornelio Procopio", ownerInfoPage.getCity());
        assertEquals("993335544", ownerInfoPage.getTelephone());
    }    
    
    @Test
    public void testErrorNoLastNameOwnerInsertion() {
        HomePage homePage = new HomePage(driver);
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        AddOwnerPage addOwnerPage = findOwnerPage.goToAddOwner();
        addOwnerPage.setFirstName("Jose")
                .setAddress("Rua das Acacias, 451")
                .setCity("Cornelio Procopio")
                .setTelephone("993335544")
                .addInvalidData();
        
        assertEquals(1, addOwnerPage.getNumberOfErrors());
    }
    
    @Test
    public void testErrorNoData() {
        HomePage homePage = new HomePage(driver);
        FindOwnerPage findOwnerPage = homePage.getMenu().goToFindOwners();
        AddOwnerPage addOwnerPage = findOwnerPage.goToAddOwner();
        addOwnerPage.addInvalidData();
        
        assertEquals(5, addOwnerPage.getNumberOfErrors());
    }    
}
