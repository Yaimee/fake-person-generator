package com.gitgudgang.fakeperson.domain.generator;

import com.gitgudgang.fakeperson.util.PhonePrefixUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class PhoneNumberGeneratorTest {
    private PhoneNumberGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new PhoneNumberGenerator();
    }

    @RepeatedTest(1000)
    void hasCorrectLengthDatatypeAndPrefix() {
        var phoneNumber = generator.generatePhoneNumber();
        var phoneNumberString = String.valueOf(phoneNumber);
        
        assertEquals(8, phoneNumberString.length());

        assertDoesNotThrow(() -> Integer.parseInt(phoneNumberString));

        assertTrue(PhonePrefixUtil.hasValidPrefix(phoneNumberString));
    }

    @Test
    void moreThanOnePrefixUsed() {
        Set<String> usedPrefixes = new HashSet<>();
        int maxAttempts = 100;

        for (int i = 0; i < maxAttempts && usedPrefixes.size() < 2; i++) {
            var phoneNumber = String.valueOf(generator.generatePhoneNumber());
            var prefix = PhonePrefixUtil.extractPrefix(phoneNumber);
            usedPrefixes.add(prefix);
        }

        assertTrue(usedPrefixes.size() > 1);
        usedPrefixes.forEach(prefix ->
                assertTrue(PhonePrefixUtil.isValidPrefix(prefix))
        );
    }
}