package com.github.petclinicpo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

/**
 *
 * @author andreendo
 */
public class ListOwnersTest_v1 {
    
    private WebDriver driver;
    private Wait<WebDriver> wait;
    
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
        
        wait = new FluentWait<WebDriver>(driver)
        		.withTimeout(Duration.ofSeconds(10))
        		.pollingEvery(Duration.ofMillis(500));
    }
    
    @After
    public void after() {
        driver.close();
    }    
    
    /**
     * Same test as ListOwnersTest.testFindInexistentOwner 
     * Keep it to show the difference.
     */
    @Test
    public void testFindInexistentOwner() {
    	// go to main page
        driver.get("http://localhost:8080/");
        
        // click in option 'find owners'
        driver.findElement(By.xpath("//a[@title='find owners']")).click();
        
        // fill the last name field
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.clear();
        lastNameField.sendKeys("Goodenough");
        
        // click to find owner button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        // wait for element to be visible (synchronization)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lastNameGroup']/div/span/div/p")));
        
        // retrieve element and verify if the error show up
        WebElement error = driver.findElement(By.xpath("//*[@id='lastNameGroup']/div/span/div/p"));
        assertEquals("has not been found", error.getText());
    }    
}
