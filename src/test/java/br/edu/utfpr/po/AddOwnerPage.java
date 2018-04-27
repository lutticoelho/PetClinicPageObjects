package br.edu.utfpr.po;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class AddOwnerPage extends PetClinicBasePage {
    //no annotation needed here -> matches html id with var name
    WebElement firstName;
    
    WebElement lastName;
    
    WebElement address;
    
    WebElement city;
    
    WebElement telephone;
    
    @FindBy(xpath = "//button[@type='submit']")
    WebElement addOwnerButton;
    
    @FindBy(xpath = "//span[@class='help-inline']")
    List<WebElement> errors;
    
    public AddOwnerPage(WebDriver driver) {
        super(driver);
    }    

    public AddOwnerPage setFirstName(String name) {
        firstName.clear();
        firstName.sendKeys(name);
        return this;
    }
    
    public AddOwnerPage setLastName(String name) {
        lastName.clear();
        lastName.sendKeys(name);
        return this;
    }

    public AddOwnerPage setAddress(String paddress) {
        address.clear();
        address.sendKeys(paddress);
        return this;
    }

    public AddOwnerPage setCity(String pcity) {
        city.clear();
        city.sendKeys(pcity);
        return this;
    }

    public AddOwnerPage setTelephone(String ptelephone) {
        telephone.clear();
        telephone.sendKeys(ptelephone);
        return this;
    }
    
    public OwnerInformationPage addValidData() {
        addOwnerButton.click();
        return new OwnerInformationPage(driver);
    }
    
    public AddOwnerPage addInvalidData() {
        addOwnerButton.click();
        return this;
    }    

    public int getNumberOfErrors() {
        return errors.size();
    }
}