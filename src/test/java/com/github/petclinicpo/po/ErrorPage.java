package com.github.petclinicpo.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class ErrorPage extends PetClinicBasePage {

    public ErrorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[@id='error-message']")
    WebElement errorMessage;

    public String getError() {
        return errorMessage.getText();
    }
    
}
