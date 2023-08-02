package com.projects.simplescript.utils;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Validator {

    public static boolean isValidEmailAddress(String email) {
        boolean isValid = true;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException e) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isNumber(String input) {
        try {
            Long.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
