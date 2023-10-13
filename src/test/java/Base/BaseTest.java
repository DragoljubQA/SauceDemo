package Base;

import Helpers.Data;
import Helpers.WebDriverFactory;
import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import static Helpers.Data.LandingURL;

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    public static ExcelReader excelReader;
    public LoginPage loginPage;
    public HomePage homePage;
    public Data data;

    @BeforeClass
    public void setUp() throws IOException {
        excelReader = new ExcelReader("TestData.xlsx");
    }

    @BeforeMethod
    public void driverSetUp() {
        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);*/
        //System.setProperty("browser", "firefox");
        System.setProperty("browser", "edge");
        //System.setProperty("browser", "safari");
        WebDriverFactory.setupDriver();
        driver = WebDriverFactory.createWebDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean elementIsDisplayed(WebElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isDisplayed;
    }



}
