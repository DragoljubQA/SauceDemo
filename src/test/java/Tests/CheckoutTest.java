package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.HeaderPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.Data.*;
import static Helpers.URLs.CARTURL;
import static Helpers.URLs.CHECKOUTURL;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        headerPage = new HeaderPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        logIn(validUsername, validPassword);
    }

    @Test(priority = 10)
    public void allFieldsAreMandatory() {
        addAnyItemToCart();
        homePage.clickOnCartButton();
        cartPage.clickOnCheckout();
        checkoutPage.clickOnContinue();
        Assert.assertEquals(checkoutPage.ErrorOnFields.size(), 3);
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUTURL);
    }

    @Test(priority = 20)
    public void firstNameIsMandatory() {
        addAnyItemToCart();
        homePage.clickOnCartButton();
        cartPage.clickOnCheckout();
        checkoutPage.inputLastName(fakerLastName());
        checkoutPage.inputZipcode(fakerPostalCode());
        checkoutPage.clickOnContinue();
        Assert.assertEquals(checkoutPage.ErrorOnFields.size(), 3);
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUTURL);
    }

    @Test(priority = 30)
    public void lastNameIsMandatory() {
        addAnyItemToCart();
        homePage.clickOnCartButton();
        cartPage.clickOnCheckout();
        checkoutPage.inputFirstName(fakerFirstName());
        checkoutPage.inputZipcode(fakerPostalCode());
        checkoutPage.clickOnContinue();
        Assert.assertEquals(checkoutPage.ErrorOnFields.size(), 3);
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUTURL);
    }

    @Test(priority = 40)
    public void zipCodeIsMandatory() {
        addAnyItemToCart();
        homePage.clickOnCartButton();
        cartPage.clickOnCheckout();
        checkoutPage.inputFirstName(fakerFirstName());
        checkoutPage.inputLastName(fakerLastName());
        checkoutPage.clickOnContinue();
        Assert.assertEquals(checkoutPage.ErrorOnFields.size(), 3);
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
        Assert.assertEquals(driver.getCurrentUrl(), CHECKOUTURL);
    }


}
