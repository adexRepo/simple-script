package com.projects.simplescript.utils;

import java.util.ArrayList;
import java.util.List;

public class AddressGenerated {
     public static List<String> generateDummyAddresses(int count) {
        List<String> addresses = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String address = "Jalan " + generateRandomNumber(100) + ", Kelurahan " + generateRandomString(5) +
                             ", Kecamatan " + generateRandomString(8) + ", Kota/Kabupaten " + generateRandomString(10);
            addresses.add(address);
        }

        return addresses;
    }

    public static int generateRandomNumber(int max) {
        return (int) (Math.random() * max) + 1;
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
