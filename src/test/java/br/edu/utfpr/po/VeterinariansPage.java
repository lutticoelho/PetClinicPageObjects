package br.edu.utfpr.po;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class VeterinariansPage extends PetClinicBasePage {

    @FindBy(xpath = "//*[@id='vets']/tbody/tr")
    List<WebElement> vets;
    
    @FindBy(linkText = "View as XML")
    WebElement xmlLink;
    
    @FindBy(linkText = "View as JSON")
    WebElement jsonLink;
    
    public VeterinariansPage(WebDriver driver) {
        super(driver);
    }
    
    public int getNumberOfVets() {
        return vets.size();
    }

    public VeterinariansPage viewXML() {
        xmlLink.click();
        return this;
    }

    public VeterinariansPage viewJSON() {
        jsonLink.click();
        return this;
    }

    public VeterinariansPage goBack() {
        driver.navigate().back();
        return this;
    }
}
