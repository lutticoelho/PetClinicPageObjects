package br.edu.utfpr.po;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class OwnerInformationPage extends PetClinicBasePage {
    
    @FindBy(xpath = "//td")
    List<WebElement> ownerData;
    
    @FindBy(xpath = "//a[contains(text(),'Edit')]")
    WebElement editOwnerButton;
    
    public OwnerInformationPage(WebDriver driver) {
        super(driver);
    }
    
    public String getName() {
        return ownerData.get(0).getText();
    }

    public Object getAddress() {
        return ownerData.get(1).getText();
    }

    public Object getCity() {
        return ownerData.get(2).getText();
    }

    public Object getTelephone() {
        return ownerData.get(3).getText();
    }

    public EditOwnerPage clickEditButton() {
        editOwnerButton.click();
        return new EditOwnerPage(driver);
    }
}
