package br.edu.utfpr.po;

import org.openqa.selenium.WebDriver;

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
