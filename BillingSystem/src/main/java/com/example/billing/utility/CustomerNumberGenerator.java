package com.example.billing.utility;
public class CustomerNumberGenerator {

    public static String generateCustomerNumber(int count) {
        return String.format("Cus%03d", count + 1);
    }
}



