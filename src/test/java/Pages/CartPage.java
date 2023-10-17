package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item_label")
    public List<WebElement> ItemInCart;

    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> RemoveButton;

    @FindBy(id = "checkout")
    public WebElement CheckoutButton;

    @FindBy(id = "continue-shopping")
    public WebElement ContinueShoppingButton;

    //--------------------------------

    public void removeItemsFromCart() {
        if(!RemoveButton.isEmpty()) {
            while(!RemoveButton.isEmpty()) {
                RemoveButton.get(0).click();
            }
        }
    }

    public void clickOnContinueShopping() {
        ContinueShoppingButton.click();
    }

    public void clickOnCheckout() {
        CheckoutButton.click();
    }
}
