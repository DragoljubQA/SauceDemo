package Base;

import Helpers.Data;
import Helpers.WebDriverFactory;
import Pages.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static Helpers.Data.*;
import static Helpers.URLs.LOGINURL;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public static ExcelReader excelReader;
    public LoginPage loginPage;
    public HomePage homePage;
    public Data data;
    public CartPage cartPage;
    public HeaderPage headerPage;
    public CheckoutPage checkoutPage;
    public PaymentPage paymentPage;
    public ThankYouPage thankYouPage;
    public SoftAssert softAssert;

    @BeforeClass
    public void setUp() throws IOException {
        excelReader = new ExcelReader("TestData.xlsx");
        softAssert = new SoftAssert();
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.navigate().to(LOGINURL);
        loginPage = new LoginPage(driver);
    }

    public void logIn(String username, String password) {
        loginPage.insertUsername(username);
        loginPage.insertPassword(password);
        loginPage.clickOnLoginButton();
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isDisplayed;
    }

    public void addAnyItemToCart() {
        for (int i = 0; i < homePage.getAllItemNames().size(); i++) {
            String changedName = getRandomItem().toLowerCase().replaceAll(" ", "-");
            String locator = "add-to-cart-" + changedName;
            boolean found = false;
            try {
                found = driver.findElement(By.id(locator)).isDisplayed();
                driver.findElement(By.id(locator)).click();
            } catch (Exception e) {
                System.out.printf(e.toString());
            }
            if (found) {
                break;
            }
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        softAssert.assertAll();
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP){
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File savedScreenshot = new File("target/screenshots/"+System.currentTimeMillis()+".jpg");
            FileUtils.copyFile(screenshot, savedScreenshot);
        }
        //driver.quit();
    }

    @AfterClass
    public void killProcesses() throws Exception {
        String browser = System.getProperty("browser");
        if (browser == null) browser = "chrome";
        switch (browser) {
            case "edge":
                Runtime.getRuntime().exec("end-edge-processes.bat");
                Runtime.getRuntime().exec("end-edge-webdriver-processes.bat");
                break;

            case "chrome":
                Runtime.getRuntime().exec("end-chrome-processes.bat");
                Runtime.getRuntime().exec("end-chrome-webdriver-processes.bat");
                break;

            case "firefox":
                Runtime.getRuntime().exec("end-firefox-processes.bat");
                Runtime.getRuntime().exec("end-firefox-webdriver-processes.bat");
                break;

            default: throw new Exception("Unknown browser");


        }

    }
}
