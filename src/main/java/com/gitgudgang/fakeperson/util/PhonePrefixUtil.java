package com.gitgudgang.fakeperson.util;

import java.util.ArrayList;
import java.util.List;

public class PhonePrefixUtil {

    private static final List<String> VALID_PREFIXES = new ArrayList<>();

    static {
        initializePrefixes();
    }

    private static void initializePrefixes() {
        String[] prefixRanges = {
                "2", "30", "31", "40", "41", "42", "50", "51", "52", "53", "60", "61", "71", "81", "91", "92", "93",
                "342", "344-349", "356-357", "359", "362", "365-366", "389", "398", "431", "441", "462", "466",
                "468", "472", "474", "476", "478", "485-486", "488-489", "493-496", "498-499", "542-543", "545",
                "551-552", "556", "571-574", "577", "579", "584", "586-587", "589", "597-598", "627", "629", "641",
                "649", "658", "662-665", "667", "692-694", "697", "771-772", "782-783", "785-786", "788-789",
                "826-827", "829"
        };

        for (String range : prefixRanges) {
            if (range.contains("-")) {
                String[] parts = range.split("-");
                int start = Integer.parseInt(parts[0]);
                int end = Integer.parseInt(parts[1]);
                for (int i = start; i <= end; i++) {
                    VALID_PREFIXES.add(String.valueOf(i));
                }
            } else {
                VALID_PREFIXES.add(range);
            }
        }
    }

    public static List<String> getValidPrefixes() {
        return new ArrayList<>(VALID_PREFIXES);
    }

    public static boolean isValidPrefix(String prefix) {
        return VALID_PREFIXES.contains(prefix);
    }

    public static boolean hasValidPrefix(String phoneNumber) {
        for (String prefix : VALID_PREFIXES) {
            if (phoneNumber.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public static String extractPrefix(String phoneNumber) {
        for (String prefix : VALID_PREFIXES) {
            if (phoneNumber.startsWith(prefix)) {
                return prefix;
            }
        }
        return "";
    }
}
