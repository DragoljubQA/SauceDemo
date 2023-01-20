package Helpers;

import com.github.javafaker.Faker;

import static Base.BaseTest.excelReader;

public class Data {
    public static final String LandingURL = excelReader.getStringData("URL", 1, 0);
    public static final String HomeURL = excelReader.getStringData("URL", 1, 1);
    public static final String validUsername = excelReader.getStringData("Login", 1, 0);
    public static final String validPassword = excelReader.getStringData("Login", 1, 1);
    Faker faker = new Faker();
    public Faker fakerUsername() {
        faker.name().username();
        return faker;
    }

    public String fakerPassword() {
        faker.internet().password(5,10, true);
        return faker.toString();
    }
}
