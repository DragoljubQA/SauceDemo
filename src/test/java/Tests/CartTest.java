package Tests;

import Base.BaseTest;
import Base.RetryAnalyzer;
import Pages.CartPage;
import Pages.HeaderPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.Data.*;
import static Helpers.URLs.CARTURL;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        headerPage = new HeaderPage(driver);
        logIn(validUsername, validPassword);
    }

    @AfterMethod
    public void clearCart() {
        cartPage.removeItemsFromCart();
    }

    @Test(priority = 10, retryAnalyzer = RetryAnalyzer.class)
    public void addItemToCart() {
        Assert.assertFalse(elementIsDisplayed(headerPage.CartBadge));
        addAnyItemToCart();
        Assert.assertEquals(headerPage.getBadgeNumber(), "1");
        homePage.clickOnCartButton();
        Assert.assertTrue(cartPage.ItemInCart.get(0).isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), CARTURL);
    }

    @Test(priority = 20, retryAnalyzer = RetryAnalyzer.class)
    public void itemCanBeRemovedFromCart() {
        addAnyItemToCart();
        homePage.clickOnCartButton();
        cartPage.removeItemsFromCart();
        Assert.assertTrue(cartPage.ItemInCart.isEmpty());
        Assert.assertEquals(driver.getCurrentUrl(), CARTURL);
    }

    @Test(priority = 30, retryAnalyzer = RetryAnalyzer.class)
    public void multipleItemsCaneBeAddedToCart() {
        Assert.assertFalse(elementIsDisplayed(headerPage.CartBadge));
        addAnyItemToCart();
        addAnyItemToCart();
        addAnyItemToCart();
        Assert.assertEquals(headerPage.getBadgeNumber(), "3");
        homePage.clickOnCartButton();
        Assert.assertEquals(cartPage.ItemInCart.size(), 3);
        Assert.assertEquals(driver.getCurrentUrl(), CARTURL);
    }

    @Test(priority = 40, retryAnalyzer = RetryAnalyzer.class)
    public void userCanContinueShopping() {
        addAnyItemToCart();
        homePage.clickOnCartButton();
        cartPage.clickOnContinueShopping();
        addAnyItemToCart();
        homePage.clickOnCartButton();
        Assert.assertEquals(headerPage.getBadgeNumber(), "2");
        Assert.assertEquals(driver.getCurrentUrl(), CARTURL);
    }


}
