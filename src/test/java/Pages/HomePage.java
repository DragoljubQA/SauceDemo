package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BaseTest {
    public HomePage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "shopping_cart_container")
    public WebElement CartButton;

    @FindBy(className = "product_sort_container")
    public WebElement SortButton;

    @FindBy(className = "bm-menu-wrap")
    public WebElement HiddenMenu;

    @FindBy(css = ".bm-item.menu-item")
    public List<WebElement> HamburgerMenuItems;

    @FindBy(id = "inventory_sidebar_link")
    public WebElement AllItemsButton;

    @FindBy(id = "about_sidebar_link")
    public WebElement AboutButton;

    @FindBy(id = "logout_sidebar_link")
    public WebElement LogoutButton;

    @FindBy(id = "reset_sidebar_link")
    public WebElement ResetButton;

    //----------------------

    public void clickOnCartButton() {
        CartButton.click();
    }

}
