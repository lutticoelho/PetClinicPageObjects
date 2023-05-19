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

import com.github.petclinicpo.po.HomePage;
import com.github.petclinicpo.po.VeterinariansPage;

/**
 *
 * @author andreendo
 */
public class VeterinarianTest {
    
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
    public void testVetsListed() {
        HomePage homePage = new HomePage(driver);
        
        VeterinariansPage vetPage = homePage.getMenu().goToVeterinarians();
        assertEquals("Veterinarians", vetPage.getTitle());
        assertEquals(5, vetPage.getNumberOfVets());
    }        
}
