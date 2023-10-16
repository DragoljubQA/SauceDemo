package Tests;

import Base.BaseTest;
import Pages.HeaderPage;
import Pages.HomePage;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static Helpers.Data.*;

public class HomepageTest extends BaseTest {
    Select select;

    @BeforeMethod
    public void pageSetUp() {
        homePage = new HomePage(driver);
        headerPage = new HeaderPage(driver);
        logIn(validUsername, validPassword);
        select = new Select(homePage.Sort);
    }

    @Test
    public void hamburgerMenuIsHidden() {
        Assert.assertEquals(homePage.HiddenMenu.getAttribute("aria-hidden"), "true");
    }

    @Test
    public void hamburgerMenuHasExpectedItems() {
        Assert.assertEquals(headerPage.HamburgerMenuItems.size(), 4);
        headerPage.openHamburgerMenu();
        waitForVisibility(headerPage.AllItemsButton);
        Assert.assertEquals(headerPage.AllItemsButton.getText(), "All Items");
        Assert.assertEquals(headerPage.AboutButton.getText(), "About");
        Assert.assertEquals(headerPage.LogoutButton.getText(), "Logout");
        Assert.assertEquals(headerPage.ResetButton.getText(), "Reset App State");
    }

    @Test
    public void sortItemsAtoZ() {
        Assert.assertEquals(homePage.getActiveSort(), "Name (A to Z)");
        Assert.assertEquals(homePage.getAllItemNames(), listOfItems());
        select.selectByValue("az");
        Assert.assertEquals(homePage.getActiveSort(), "Name (A to Z)");
        Assert.assertEquals(homePage.getAllItemNames(), homePage.getReversedList(listOfItems()));
    }

    @Test
    public void sortItemsZtoA() {
        Assert.assertEquals(homePage.getActiveSort(), "Name (A to Z)");
        Assert.assertEquals(homePage.getAllItemNames(), listOfItems());
        select.selectByValue("za");
        Assert.assertEquals(homePage.getActiveSort(), "Name (Z to A)");
        Assert.assertEquals(homePage.getAllItemNames(), homePage.getReversedList(listOfItems()));
    }

    @Test
    public void sortPriceHighToLow() throws Exception {
        select.selectByValue("hilo");
        Assert.assertTrue(homePage.numbersAreSortedHighToLow("highlow",homePage.getPrices()));
    }

    @Test
    public void sortPriceLowToHigh() throws Exception {
        select.selectByValue("lohi");
        Assert.assertTrue(homePage.numbersAreSortedHighToLow("lowhigh",homePage.getPrices()));
    }




}
