package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement Username;

    @FindBy(id = "password")
    public WebElement Password;

    @FindBy(id = "login-button")
    public WebElement LoginButton;

    //-----------------------

    public void insertUsername(String username) {
        Username.clear();
        Username.sendKeys(username);
    }

    public void insertPassword(String password) {
        Password.clear();
        Password.sendKeys(password);
    }

    public void clickOnLoginButton() {
        LoginButton.click();
    }

}
