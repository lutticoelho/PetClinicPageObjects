package com.github.petclinicpo.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class FindOwnerPage extends PetClinicBasePage {
    
    @FindBy(id = "lastName")
    WebElement lastNameField;
    
    @FindBy(xpath = "//button[@type='submit']")
    WebElement findButton;
    
    @FindBy(xpath = "//a[@href='/owners/new']")
    WebElement addButton;
    
    @FindBy(xpath = "//*[@id='lastNameGroup']/div/span/div/p")
    WebElement error;
    
    public FindOwnerPage(WebDriver driver) {
        super(driver);
    }
    
    public FindOwnerPage setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        
        return this;
    }
    
    public ListOwnersPage clickFindButton() {
        findButton.click();
        return new ListOwnersPage(driver);
    }
    
    public String getErrorMessage() {
        if(error != null)
            return error.getText();
        
        return "";
    }

    public EditOwnerPage goToAddOwner() {
        addButton.click();
        
        return new EditOwnerPage(driver);
    }
}
