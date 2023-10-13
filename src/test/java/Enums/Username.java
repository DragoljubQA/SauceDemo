package Enums;

import java.util.Random;

public enum Username {
    standard_user,
    locked_out_user,
    problem_user,
    performance_glitch_user;

    public static String getRandom() {
        return values()[new Random().nextInt(values().length)].name();
    }
}
