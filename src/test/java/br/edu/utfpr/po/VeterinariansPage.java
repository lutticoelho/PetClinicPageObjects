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
    
    public VeterinariansPage(WebDriver driver) {
        super(driver);
    }
    
    public int getNumberOfVets() {
        return vets.size();
    }
}
