package com.github.petclinicpo.po;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class EditOwnerPage extends PetClinicBasePage {
    //no annotation needed here -> matches html id with var name
    WebElement firstName;
    
    WebElement lastName;
    
    WebElement address;
    
    WebElement city;
    
    WebElement telephone;
    
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;
    
    @FindBy(xpath = "//span[@class='help-inline']")
    List<WebElement> errors;
    
    public EditOwnerPage(WebDriver driver) {
        super(driver);
    }    

    public EditOwnerPage setFirstName(String name) {
        firstName.clear();
        firstName.sendKeys(name);
        return this;
    }
    
    public EditOwnerPage setLastName(String name) {
        lastName.clear();
        lastName.sendKeys(name);
        return this;
    }

    public EditOwnerPage setAddress(String paddress) {
        address.clear();
        address.sendKeys(paddress);
        return this;
    }

    public EditOwnerPage setCity(String pcity) {
        city.clear();
        city.sendKeys(pcity);
        return this;
    }

    public EditOwnerPage setTelephone(String ptelephone) {
        telephone.clear();
        telephone.sendKeys(ptelephone);
        return this;
    }
    
    public OwnerInformationPage addValidData() {
        submitButton.click();
        return new OwnerInformationPage(driver);
    }
    
    public EditOwnerPage addInvalidData() {
        submitButton.click();
        return this;
    }    

    public int getNumberOfErrors() {
        return errors.size();
    }

    public String getErrorMessage(int index) {
        return errors.get(index).getText();
    }

    public String getFirstName() {
        return firstName.getAttribute("value");
    }

    public String getLastName() {
        return lastName.getAttribute("value");
    }

    public String getAddress() {
        return address.getAttribute("value");
    }

    public String getCity() {
        return city.getAttribute("value");
    }

    public String getTelephone() {
        return telephone.getAttribute("value");
    }
    
}