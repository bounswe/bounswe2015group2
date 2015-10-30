package edu.boun.cmpe451.group2.utils;

/**
 * String Utility class
 */
public class StringUtil {

    /**
     * checks if string is empty or null
     *
     * @param str string to be checked.
     * @return true - empty or null, false - not
     */
    public static boolean isEmpty(String str) {
        if (str == null)
            return true;

        if ("".equals(str))
            return true;

        return false;
    }
}
