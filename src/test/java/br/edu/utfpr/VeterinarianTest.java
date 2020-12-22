package br.edu.utfpr;

import br.edu.utfpr.po.HomePage;
import br.edu.utfpr.po.VeterinariansPage;
import io.github.bonigarcia.wdm.WebDriverManager;
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
public class VeterinarianTest {
    
    private WebDriver driver;
    
    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
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
    public void testVetsListed() {
        HomePage homePage = new HomePage(driver);
        
        VeterinariansPage vetPage = homePage.getMenu().goToVeterinarians();
        assertEquals("Veterinarians", vetPage.getTitle());
        assertEquals(6, vetPage.getNumberOfVets());
    }        
}
