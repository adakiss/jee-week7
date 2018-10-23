package hu.adamkiss.hoeweek7.servlet;

import java.util.regex.Pattern;

public class Utils {
    public static boolean isNumber(String s) {
        return (s != null) && Pattern.compile("^(0|[1-9][0-9]*)$").matcher(s).matches();
    }
}