package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HeaderPage {

    public HeaderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "bm-burger-button")
    public WebElement OpenHamburgerMenu;

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

    @FindBy(className = "shopping_cart_badge")
    public WebElement CartBadge;

    //--------------------------------

    public String getBadgeNumber() {
        return CartBadge.getText();
    }


    public void openHamburgerMenu() {
        OpenHamburgerMenu.click();
    }

    public void clickOnAboutButton() {
        AboutButton.click();
    }

    public void clickOnLogoutButton() {
        LogoutButton.click();
    }
}
