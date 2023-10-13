package Tests;

import Base.BaseTest;
import Helpers.WebDriverFactory;
import Pages.HomePage;
import Pages.LoginPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static Helpers.Data.*;
import Helpers.Data;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to(LandingURL);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        data = new Data();
    }

    public void logIn(String username, String password) {
        loginPage.insertUsername(username);
        loginPage.insertPassword(password);
        loginPage.clickOnLoginButton();
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
        homePage.openHamburgerMenu();
        homePage.clickOnLogoutButton();
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
