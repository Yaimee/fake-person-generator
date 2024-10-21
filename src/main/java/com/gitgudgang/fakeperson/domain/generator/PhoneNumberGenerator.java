package com.gitgudgang.fakeperson.domain.generator;

import com.gitgudgang.fakeperson.util.PhonePrefixUtil;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@Service
public class PhoneNumberGenerator {
    private static final List<String> VALID_PREFIXES = PhonePrefixUtil.getValidPrefixes();
    private final Faker faker = new Faker();
    private static final Random RANDOM = new Random();

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
