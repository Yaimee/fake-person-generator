package com.gitgudgang.fakeperson.domain.generator;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@Service
public class PhoneNumberGenerator {
    private static final List<String> VALID_PREFIXES = new ArrayList<>();
    private final Faker faker = new Faker();
    private final Random RANDOM = new Random();

    // Generates a static array of valid phone number prefixes where ranges have been expanded
    static {
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

    public int generatePhoneNumber() {
        return Integer.parseInt(generatePhoneNumberString());
    }

    private String generatePhoneNumberString() {
        //TODO: Change phone number to String instead of an int for easier handling
        var prefix = VALID_PREFIXES.get(RANDOM.nextInt(VALID_PREFIXES.size()));
        var remainingDigits = 8 - prefix.length();
        var number = new StringBuilder(prefix);
        for (int i = 0; i < remainingDigits; i++) {
            number.append(RANDOM.nextInt(10));
        }

        return number.toString();
    }
}
