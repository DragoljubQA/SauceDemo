package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static Helpers.Data.*;

public class HomepageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);*/
        driver = new EdgeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(LandingURL);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    public void logIn(String username, String password) {
        loginPage.insertUsername(username);
        loginPage.insertPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Test
    public void hamburgerMenuIsHidden() {
        logIn(validUsername, validPassword);
        Assert.assertEquals(homePage.HiddenMenu.getAttribute("aria-hidden"), "true");
    }

    @Test
    public void hamburgerMenuHasExpectedItems() {
        logIn(validUsername, validPassword);
        Assert.assertEquals(homePage.HamburgerMenuItems.size(), 4);
        /*Assert.assertEquals(homePage.AllItemsButton.getText(), "All Items");
        Assert.assertEquals(homePage.AboutButton.getText(), "About");
        Assert.assertEquals(homePage.LogoutButton.getText(), "Logout");
        Assert.assertEquals(homePage.ResetButton.getText(), "Reset App State");*/
        /*Assert.assertEquals(homePage.HamburgerMenuItems.get(0).getText(), "All Items");
        Assert.assertEquals(homePage.HamburgerMenuItems.get(1).getText(), "About");
        Assert.assertEquals(homePage.HamburgerMenuItems.get(2).getText(), "Logout");
        Assert.assertEquals(homePage.HamburgerMenuItems.get(3).getText(), "Reset App State");*/
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
