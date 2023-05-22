package com.github.petclinicpo.tests.gw;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.AfterExecution;
import org.graphwalker.java.annotation.BeforeExecution;
import org.graphwalker.java.annotation.GraphWalker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.github.petclinicpo.po.HomePage;
import com.github.petclinicpo.po.PetClinicBasePage;

import io.github.bonigarcia.wdm.WebDriverManager;

//@GraphWalker(value = "random(edge_coverage(100))")
//@GraphWalker(value = "random(edge_coverage(100)) random(time_duration(10))")
@GraphWalker(value = "random(time_duration(30))")
public class PetClinicNavigationMBTest extends ExecutionContext implements PetClinicNavigation {
	
	private WebDriver driver;
	private PetClinicBasePage currentPage;
	
	@BeforeExecution
	public void setup() {
		WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("headless");				
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        currentPage = new HomePage(driver);
	}
	
	@AfterExecution
	public void cleanup() {
		driver.close();
	}
	
	//--------------------------STATES------------------------------------------//
	@Override
	public void v_HomePage() {
		assertEquals("Welcome", currentPage.getTitle());
	}

	@Override
	public void v_VeterinariansPage() {
		assertEquals("Veterinarians", currentPage.getTitle());
	}
	
	@Override
	public void v_ErrorPage() {
		assertEquals("Something happened...", currentPage.getTitle());
	}

	@Override
	public void v_FindOwnerPage() {
		assertEquals("Find Owners", currentPage.getTitle());
	}
	
	//--------------------------EVENTS------------------------------------------//

	@Override
	public void e_click_home() {
		currentPage = currentPage.getMenu().goToHome();
	}

	@Override
	public void e_click_vet() {
		currentPage = currentPage.getMenu().goToVeterinarians();
	}

	@Override
	public void e_click_owner() {
		currentPage = currentPage.getMenu().goToFindOwners();
	}

	@Override
	public void e_click_error() {
		currentPage = currentPage.getMenu().goToError();
	}

}
