package com.projects.simplescript.utils;

import java.time.LocalDate;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.bouncycastle.util.encoders.Base64;

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

    public static boolean checkCredential(String springKey, String fxKey, String fin) {
        LocalDate inputDate = LocalDate.parse(decodeBase64(springKey));
        boolean isAfter = !inputDate.isAfter(LocalDate.now());
        String builder = encodeBase64(isAfter + springKey + fxKey);
        return fin.equals(builder);
    }

    public static boolean checkAppType(String secret, String secret2, String app) {
        LocalDate inputDate = LocalDate.parse(decodeBase64(secret2));
        boolean isAfter = !inputDate.isAfter(LocalDate.now());
        String keyBuilder = encodeBase64(secret.replace(" ", "-")) + secret2 + isAfter;
        return app.equals(keyBuilder);
    }

    public static String encodeBase64(String input) {
        byte[] encodedBytes = Base64.encode(input.getBytes());
        return new String(encodedBytes);
    }

    public static String decodeBase64(String encodedInput) {
        byte[] decodedBytes = Base64.decode(encodedInput.getBytes());
        return new String(decodedBytes);
    }
}
