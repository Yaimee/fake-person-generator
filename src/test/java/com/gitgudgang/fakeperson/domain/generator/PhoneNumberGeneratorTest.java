package com.gitgudgang.fakeperson.domain.generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class PhoneNumberGeneratorTest {
    private PhoneNumberGenerator phoneNumberGenerator = new PhoneNumberGenerator();

    @ParameterizedTest
    @ValueSource(strings = {""})
    void phoneNumber_ValidPrefix() {
    }

    @Test
    void phoneNumber_validLength() {
    }

    @Test
    void phoneNumber_invalidLength() {
    }

    @Test
    void notANumber() {
        
    }

}