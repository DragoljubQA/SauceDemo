package Enums;

import java.util.Random;

public enum Inventory {
    Sauce_Labs_Backpack,
    Sauce_Labs_Bike_Light,
    Sauce_Labs_Bolt_T__Shirt,
    Sauce_Labs_Fleece_Jacket,
    Sauce_Labs_Onesie;


    public static String getRandom() {
        String random = values()[new Random().nextInt(values().length)].name();
        String newString = random.replaceAll("__", "-");
        String newString1 = newString.replaceAll("_", " ");
        return newString1;
    }

}
