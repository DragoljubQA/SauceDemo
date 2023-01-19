package Helpers;

import Base.BaseTest;

import java.util.HashMap;

public class Data extends BaseTest {
    public static final String LandingURL = excelReader.getStringData("URL", 1, 0);
    public static final String HomeURL = excelReader.getStringData("URL", 1, 1);
    public static final String validUsername = excelReader.getStringData("Login", 1, 0);
    public static final String validPassword = excelReader.getStringData("Login", 1, 1);

    public static void users() {
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
    }
}
