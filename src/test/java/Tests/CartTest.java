package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.Data.*;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        logIn(validUsername, validPassword);
    }

    @Test(priority = 10)
    public void addItemToCart() {
        Assert.assertFalse(elementIsDisplayed(homePage.CartBadge));
        addAnyItemToCart();
        Assert.assertEquals(homePage.getBadgeNumber(), "1");
        homePage.clickOnCartButton();
        Assert.assertTrue(cartPage.ItemInCart.isDisplayed());
    }

    @Test(priority = 20)
    public void itemCanBeRemovedFromCart() {
        addAnyItemToCart();
        homePage.clickOnCartButton();
        cartPage.clickOnRemove();
        Assert.assertFalse(elementIsDisplayed(cartPage.ItemInCart));
    }


}
