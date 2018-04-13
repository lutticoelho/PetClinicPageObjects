package br.edu.utfpr.po;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class ListOwnersPage extends PetClinicBasePage {
    
    @FindBy(xpath = "//*[@id='vets']/tbody/tr")
    List<WebElement> rows;
    
    public ListOwnersPage(WebDriver driver) {
        super(driver);
    }
    
    public int getNumberOfOwners() {
        return rows.size();
    }
    
    public String getRow(int index) {
        return rows.get(index).getText();
    }
}
