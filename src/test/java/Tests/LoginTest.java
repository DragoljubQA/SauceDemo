package Tests;

import Base.BaseTest;
import Base.RetryAnalyzer;
import Pages.HeaderPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Helpers.Data.*;
import static Helpers.URLs.HOMEPAGEURL;
import static Helpers.URLs.LOGINURL;

import Helpers.Data;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        headerPage = new HeaderPage(driver);
        data = new Data();
    }

    @Test (priority = 10, retryAnalyzer = RetryAnalyzer.class)
    public void userCanLogIn() {
        logIn(validUsername, validPassword);
        Assert.assertTrue(homePage.CartButton.isDisplayed());
        Assert.assertTrue(homePage.SortButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), HOMEPAGEURL);
    }

    @Test (priority = 15, retryAnalyzer = RetryAnalyzer.class)
    public void userCanLogOut() {
        logIn(validUsername, validPassword);
        headerPage.openHamburgerMenu();
        headerPage.clickOnLogoutButton();
        Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
        Assert.assertTrue(loginPage.LoginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), LOGINURL);
    }

    @Test (priority = 20, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLogInWithInvalidUsername() {
        logIn("username", validPassword);
        Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
        Assert.assertNotEquals(driver.getCurrentUrl(), HOMEPAGEURL);
    }

    @Test (priority = 30, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLogInWithInvalidPassword() {
        logIn(validUsername, "password");
        Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
        Assert.assertNotEquals(driver.getCurrentUrl(), HOMEPAGEURL);
    }

    @Test (priority = 40, dataProvider = "usernameAndPassword", dataProviderClass = Data.class, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLogInWithInvalidUsernameAndPassword(String username, String password) {
        logIn(username, password);
        Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
        Assert.assertNotEquals(driver.getCurrentUrl(), HOMEPAGEURL);
    }

    @Test (priority = 50, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLogInWithRandomData() {
        for (int i = 0; i < 10; i++) {
            logIn(fakerUsername(), fakerPassword());
            Assert.assertFalse(elementIsDisplayed(homePage.CartButton));
            Assert.assertNotEquals(driver.getCurrentUrl(), HOMEPAGEURL);
        }

    }

}
