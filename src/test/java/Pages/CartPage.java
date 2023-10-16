package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item_label")
    public WebElement ItemInCart;

    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public WebElement RemoveButton;

    //--------------------------------

    public void clickOnRemove() {
        RemoveButton.click();
    }
}
