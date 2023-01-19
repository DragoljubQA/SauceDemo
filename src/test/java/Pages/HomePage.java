package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseTest {
    public HomePage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "shopping_cart_container")
    public WebElement CartButton;

    @FindBy(className = "product_sort_container")
    public WebElement SortButton;

    //----------------------

    public void clickOnCartButton() {
        CartButton.click();
    }

}
