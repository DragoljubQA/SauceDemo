package Tests;

import Base.BaseTest;
import Pages.HeaderPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.Data.*;
import Helpers.Data;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        headerPage = new HeaderPage(driver);
        data = new Data();
    }

    @Test (priority = 10)
    public void userCanLogIn() {
        logIn(validUsername, validPassword);
        Assert.assertEquals(driver.getCurrentUrl(), HomeURL);
        Assert.assertTrue(homePage.CartButton.isDisplayed());
        Assert.assertTrue(homePage.SortButton.isDisplayed());
    }

    @Test (priority = 15)
    public void userCanLogOut() {
        logIn(validUsername, validPassword);
        headerPage.openHamburgerMenu();
        headerPage.clickOnLogoutButton();
        Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
        Assert.assertEquals(driver.getCurrentUrl(), LandingURL);
        Assert.assertTrue(loginPage.LoginButton.isDisplayed());
    }

    @Test (priority = 20)
    public void userCannotLogInWithInvalidUsername() {
        logIn("username", validPassword);
        Assert.assertNotEquals(driver.getCurrentUrl(), HomeURL);
        Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
        Assert.assertFalse(elementIsDisplayed(homePage.SortButton));
    }

    @Test (priority = 30)
    public void userCannotLogInWithInvalidPassword() {
        logIn(validUsername, "password");
        Assert.assertNotEquals(driver.getCurrentUrl(), HomeURL);
        Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
        Assert.assertFalse(elementIsDisplayed(homePage.SortButton));
    }

    @Test (priority = 40, dataProvider = "usernameAndPassword", dataProviderClass = Data.class)
    public void userCannotLogInWithInvalidUsernameAndPassword(String username, String password) {
        logIn(username, password);
        Assert.assertNotEquals(driver.getCurrentUrl(), HomeURL);
        Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
        Assert.assertFalse(elementIsDisplayed(homePage.SortButton));
    }

    @Test (priority = 50)
    public void userCannotLogInWithRandomData() {
        for (int i = 0; i < 10; i++) {
            logIn(fakerUsername(), fakerPassword());
            Assert.assertNotEquals(driver.getCurrentUrl(), HomeURL);
            Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
            Assert.assertFalse(elementIsDisplayed(homePage.SortButton));
        }

    }

}
