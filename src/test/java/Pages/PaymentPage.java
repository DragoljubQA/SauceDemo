package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage {
    public PaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cancel")
    public WebElement CancelButton;

    @FindBy(id = "finish")
    public WebElement FinishButton;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> Items;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> ItemsPrice;

    @FindBy(className = "summary_info")
    public WebElement PaymentInfo;



    //--------------------------

    public void clickOnCancelButton() {
        CancelButton.click();
    }

    public void clickOnFinishButton() {
        FinishButton.click();
    }

    public void getPaymentInfo() {
        System.out.println(PaymentInfo.getText());
    }

}
