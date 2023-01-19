package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static Helpers.Data.*;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get(LandingURL);
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    public void logIn(String username, String password) {
        loginPage.insertUsername(username);
        loginPage.insertPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Test
    public void userCanLogIn() {
        logIn(validUsername, validPassword);
        Assert.assertEquals(driver.getCurrentUrl(), HomeURL);
        Assert.assertTrue(homePage.CartButton.isDisplayed());
        Assert.assertTrue(homePage.SortButton.isDisplayed());

    }

    @Test
    public void userCannotLogInWithInvalidCredentials() {
        HashMap<String, String> users = new HashMap<String, String>();
        users.put("password", "secret");
        users.put("password", "sauce");
        users.put("password", "secret sauce");
        users.put("password", "secret 123");
        users.put("password", "secret!");
        users.put("password", "SECRET_SAUCE");
        users.put("username", "user");
        users.put("username", "username");
        users.put("username", "standard username");
        users.put("username", "standard 123");
        users.put("username", "standard!");
        users.put("username", "STANDARD_USERNAME");

        System.out.println(users.get("password"));

    }

}
