package Tests;

import Base.BaseTest;
import Helpers.Data;
import Pages.HeaderPage;
import Pages.HomePage;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.Data.*;

public class HomepageTest extends BaseTest {
    Select select;

    @BeforeMethod
    public void pageSetUp() {
        homePage = new HomePage(driver);
        headerPage = new HeaderPage(driver);
        data = new Data();
        logIn(validUsername, validPassword);
        select = new Select(homePage.Sort);
    }

    @Test(priority = 10)
    public void hamburgerMenuIsHidden() {
        Assert.assertEquals(homePage.HiddenMenu.getAttribute("aria-hidden"), "true");
    }

    @Test(priority = 20)
    public void hamburgerMenuHasExpectedItems() {
        Assert.assertEquals(headerPage.HamburgerMenuItems.size(), 4);
        headerPage.openHamburgerMenu();
        waitForVisibility(headerPage.AllItemsButton);
        Assert.assertEquals(headerPage.AllItemsButton.getText(), "All Items");
        Assert.assertEquals(headerPage.AboutButton.getText(), "About");
        Assert.assertEquals(headerPage.LogoutButton.getText(), "Logout");
        Assert.assertEquals(headerPage.ResetButton.getText(), "Reset App State");
    }

    @Test(priority = 30)
    public void sortItemsAtoZ() {
        data.setSaveList(homePage.getAllItemNames());
        select.selectByValue("az");
        Assert.assertEquals(homePage.getActiveSort(), "Name (A to Z)");
        Assert.assertEquals(homePage.getAllItemNames(), homePage.getSortedList(data.getSaveList()));
    }

    @Test(priority = 40)
    public void sortItemsZtoA() {
        data.setSaveList(homePage.getAllItemNames());
        select.selectByValue("za");
        Assert.assertEquals(homePage.getActiveSort(), "Name (Z to A)");
        Assert.assertEquals(homePage.getAllItemNames(), homePage.getReversedList(data.getSaveList()));
    }

    @Test(priority = 50)
    public void sortPriceHighToLow() throws Exception {
        select.selectByValue("hilo");
        Assert.assertTrue(homePage.numbersAreSortedHighToLow("highlow",homePage.getPrices()));
    }

    @Test(priority = 60)
    public void sortPriceLowToHigh() throws Exception {
        select.selectByValue("lohi");
        Assert.assertTrue(homePage.numbersAreSortedHighToLow("lowhigh",homePage.getPrices()));
    }




}
