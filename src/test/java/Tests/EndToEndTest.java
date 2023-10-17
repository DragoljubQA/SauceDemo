package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.Data.*;
import static Helpers.URLs.*;

public class EndToEndTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        logIn(validUsername, validPassword);
        homePage = new HomePage(driver);
        headerPage = new HeaderPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);
        thankYouPage = new ThankYouPage(driver);
    }

    @Test
    public void itemCanBeBought() {
        addAnyItemToCart();
        homePage.clickOnCartButton();
        cartPage.clickOnCheckout();
        checkoutPage.inputFirstName(fakerFirstName());
        checkoutPage.inputLastName(fakerLastName());
        checkoutPage.inputZipcode(fakerPostalCode());
        checkoutPage.clickOnContinue();
        paymentPage.clickOnFinishButton();
        Assert.assertTrue(thankYouPage.ThankYouMessage.isDisplayed());
        Assert.assertEquals(thankYouPage.thankYouMessageText(), "Thank you for your order!");
        Assert.assertEquals(thankYouPage.titleText(), "Checkout: Complete!");
        Assert.assertEquals(thankYouPage.completedText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        Assert.assertTrue(thankYouPage.BackHomeButton.isDisplayed());
        Assert.assertFalse(elementIsDisplayed(headerPage.CartBadge));
        Assert.assertEquals(driver.getCurrentUrl(), THANKYOUPAGE);

        /*softAssert.assertTrue(thankYouPage.ThankYouMessage.isDisplayed());
        softAssert.assertEquals(thankYouPage.thankYouMessageText(), "Thank you for your order!");
        softAssert.assertEquals(thankYouPage.titleText(), "Checkout: Complete!");
        softAssert.assertEquals(thankYouPage.completedText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        softAssert.assertTrue(thankYouPage.BackHomeButton.isDisplayed());*/
    }

    @Test
    public void userCanGoToHomepageAfterShopping() {
        addAnyItemToCart();
        homePage.clickOnCartButton();
        cartPage.clickOnCheckout();
        checkoutPage.inputFirstName(fakerFirstName());
        checkoutPage.inputLastName(fakerLastName());
        checkoutPage.inputZipcode(fakerPostalCode());
        checkoutPage.clickOnContinue();
        paymentPage.clickOnFinishButton();
        thankYouPage.clickBackHomeButton();
        Assert.assertEquals(driver.getCurrentUrl(), HOMEPAGEURL);
    }



}
