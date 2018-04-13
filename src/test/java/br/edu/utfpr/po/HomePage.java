package br.edu.utfpr.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class HomePage extends PetClinicBasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("http://localhost:8080/");
    }
}
