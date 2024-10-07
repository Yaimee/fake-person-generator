package com.gitgudgang.fakeperson.domain.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static com.gitgudgang.fakeperson.config.CPRConstants.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonDataGeneratorTest {
    PersonDataGenerator generator = new PersonDataGenerator();
    static LocalDate yesterday = LocalDate.now().minusDays(1);
    String validFemaleCpr = generator.generateCpr("female", VALID_DOB);
    String validMaleCpr = generator.generateCpr("male", VALID_DOB);

    // Examples of tests that only encompass one case
    @Test
    void dob_validUpperBoundary() {
        assertDoesNotThrow(() -> validFemaleCpr);
    }

    @Test
    void dob_validLowerBoundary() {
        assertDoesNotThrow(() -> generator.generateCpr("female", CPR_REGISTER_START_DATE));
    }

    @Test
    void dob_invalidUpperBoundary() {
        assertThrows(IllegalArgumentException.class, () -> generator.generateCpr("female", LocalDate.now()));
    }

    @Test
    void dob_invalidLowerBoundary() {
        assertThrows(IllegalArgumentException.class, () -> generator.generateCpr("female", CPR_REGISTER_START_DATE.minusDays(1)));
    }

    @Test
    void genderDigitFemale_valid() {
        var genderDigit = validFemaleCpr.charAt(9) - '0';
        Assertions.assertTrue(GENDER_CODES_FEMALE.contains(genderDigit));
    }

    @Test
    void genderDigitMale_valid() {
        var genderDigit = validMaleCpr.charAt(9) - '0';
        Assertions.assertTrue(GENDER_CODES_MALE.contains(genderDigit));
    }

    @Test
    void cprLength_valid() {
        Assertions.assertEquals(10, validFemaleCpr.length());
    }

    @Test
    void cprOnlyNumbers_valid() {
        assertDoesNotThrow(() -> Integer.parseInt(validFemaleCpr));
    }

    // Examples of parameterized tests
    @ParameterizedTest
    @MethodSource("centuryEncodingTestCases_valid")
    void centuryEncoding_valid(LocalDate testDate, List<Integer> expectedCodes) {
        testCenturyEncoding(testDate, expectedCodes);
    }

    private void testCenturyEncoding(LocalDate testDate, List<Integer> centuryCodes) {
        var cpr = generator.generateCpr("female", testDate);
        var centuryDigit = cpr.charAt(6) - '0';
        Assertions.assertTrue(centuryCodes.contains(centuryDigit));
    }

    private static Stream<Arguments> centuryEncodingTestCases_valid() {
        return Stream.of(
                Arguments.of(CPR_REGISTER_START_DATE, CENTURY_CODES_1900),
                Arguments.of(LocalDate.of(1960, 6, 15), CENTURY_CODES_1900),
                Arguments.of(LocalDate.of(1999, 12, 31), CENTURY_CODES_1900),
                Arguments.of(LocalDate.of(2000, 1, 1), CENTURY_CODES_2000),
                Arguments.of(LocalDate.of(2010, 12, 31), CENTURY_CODES_2000),
                Arguments.of(yesterday, CENTURY_CODES_2000)
        );
    }
}