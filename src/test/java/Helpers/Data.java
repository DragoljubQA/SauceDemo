package Helpers;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import static Base.BaseTest.excelReader;

public class Data {
    public static final String LandingURL = excelReader.getStringData("URL", 1, 0);
    public static final String HomeURL = excelReader.getStringData("URL", 1, 1);
    public static final String validUsername = excelReader.getStringData("Login", 1, 0);
    public static final String validPassword = excelReader.getStringData("Login", 1, 1);
    static Faker faker = new Faker();
    public static String fakerUsername() {
        return faker.name().username();
    }

    public static String fakerPassword() {
        return faker.internet().password(6,10, true);
    }

    @DataProvider(name = "usernameAndPassword")
    public Object[][] DataTest() {
        return new Object[][]{{"username", "password"}, {"standard", "pass_word"}, {"user", "pass"}};
    }
}
