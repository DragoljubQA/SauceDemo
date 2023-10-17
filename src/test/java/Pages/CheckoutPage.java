package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage {
    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    public WebElement FirstName;

    @FindBy(id = "last-name")
    public WebElement LastName;

    @FindBy(id = "postal-code")
    public WebElement PostalCode;

    @FindBy(id = "cancel")
    public WebElement CancelButton;

    @FindBy(id = "continue")
    public WebElement ContinueButton;

    @FindBy(css = ".input_error.form_input.error")
    public List<WebElement> ErrorOnFields;

    @FindBy(css = "h3[data-test='error']")
    public WebElement ErrorMessage;

    @FindBy(className = "error-button")
    public WebElement CloseErrorMessage;

    //--------------------------------

    public void inputFirstName(String firstName) {
        FirstName.clear();
        FirstName.sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        LastName.clear();
        LastName.sendKeys(lastName);
    }

    public void inputZipcode(String zipCode) {
        PostalCode.clear();
        PostalCode.sendKeys(zipCode);
    }

    public void clickOnCancel() {
        CancelButton.click();
    }

    public void clickOnContinue() {
        ContinueButton.click();
    }

    public String getErrorMessage() {
        return ErrorMessage.getText();
    }

    public void clickOnCloseErrorMessage() {
        CloseErrorMessage.click();
    }

}
