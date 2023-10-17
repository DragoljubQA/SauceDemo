package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYouPage {
    public ThankYouPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "complete-header")
    public WebElement ThankYouMessage;

    @FindBy(className = "title")
    public WebElement Title;

    @FindBy(className = "complete-text")
    public WebElement Completed;

    @FindBy(id = "back-to-products")
    public WebElement BackHomeButton;

    //----------------------------

    public String thankYouMessageText() {
        return ThankYouMessage.getText();
    }

    public String titleText() {
        return Title.getText();
    }

    public String completedText() {
        return Completed.getText();
    }

    public void clickBackHomeButton() {
        BackHomeButton.click();
    }

}
