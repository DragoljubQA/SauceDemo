package Helpers;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Random;

import static Base.BaseTest.excelReader;

public class Data {
    public static final String LandingURL = excelReader.getStringData("URL", 1, 0);
    public static final String HomeURL = excelReader.getStringData("URL", 1, 1);
    public static final String validUsername = excelReader.getStringData("Login", 1, 0);
    public static final String validPassword = excelReader.getStringData("Login", 1, 1);
    public static ArrayList<String> listOfItems() {
        ArrayList<String> items = new ArrayList<>();
        items.add("Sauce Labs Backpack");
        items.add("Sauce Labs Bike Light");
        items.add("Sauce Labs Bolt T-Shirt");
        items.add("Sauce Labs Fleece Jacket");
        items.add("Sauce Labs Onesie");
        items.add("Test.allTheThings() T-Shirt (Red)");
        return items;
    }

    ArrayList saveList;

    public ArrayList getSaveList() {
        return saveList;
    }

    public void setSaveList(ArrayList saveList) {
        this.saveList = saveList;
    }

    public static String getRandomItem() {
        return listOfItems().get(new Random().nextInt(listOfItems().size()));
    }

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
