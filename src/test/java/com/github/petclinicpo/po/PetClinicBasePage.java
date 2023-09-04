package com.github.petclinicpo.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class PetClinicBasePage extends BasePage {
    
    @FindBy(tagName = "h2")
    WebElement title;
    
    Menu menu;    
    
    public PetClinicBasePage(WebDriver driver) {
        super(driver);
        menu = new Menu(driver);
    }
    
    public Menu getMenu() {
        return menu;
    }
    
    public String getTitle() {
        return title.getText();
    }  
    
    public ErrorPage goToErrorPage() {
        this.driver.navigate().forward();
        return (ErrorPage)this;
    }  
}
