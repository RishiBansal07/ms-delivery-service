package com.delivery.project.utility;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UUIDExtractor {

    public String extraction(String orderId) {
        String uuid = extractUUID(orderId);

        if (uuid != null) {
            System.out.println("Extracted UUID: " + uuid);
        } else {
            System.out.println("No UUID found in the input string.");
        }

        return uuid;
    }
    private static String extractUUID(String input) {
        // Define a regex pattern for matching UUIDs
        Pattern pattern = Pattern.compile("/shippingOrders/([a-fA-F0-9\\-]+)");
        Matcher matcher = pattern.matcher(input);

        // Check if the pattern matches the input string
        if (matcher.find()) {
            // Group 1 in the matcher contains the UUID
            return matcher.group(1);
        }

        return null; // Return null if no UUID is found
    }
}
