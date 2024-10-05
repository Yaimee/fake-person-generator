package com.gitgudgang.fakeperson.domain.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.gitgudgang.fakeperson.config.CPRConstants.CENTURY_CODE_1900;
import static com.gitgudgang.fakeperson.config.CPRConstants.EARLIEST_VALID_DATE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonDataGeneratorTest {
    PersonDataGenerator generator = new PersonDataGenerator();
    LocalDate yesterday = LocalDate.now().minusDays(1);
    final LocalDate CPR_REGISTER_START_DATE = LocalDate.of(1924, 1, 1);

    @Test
    void dob_validUpperBoundary() {
        assertDoesNotThrow(() -> generator.generateCpr("female", yesterday));
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
    void centuryEncoding1900Lower_valid() {
        var cpr1900 = generator.generateCpr("female", EARLIEST_VALID_DATE);
        System.out.println("cpr1900" + cpr1900);
        var centuryDigit = cpr1900.charAt(6) - '0';
        Assertions.assertTrue(CENTURY_CODE_1900.contains(centuryDigit));
    }

    @Test
    void centuryEncoding1900Upper_valid() {
        var cpr1999 = generator.generateCpr("female", LocalDate.of(1999, 12, 31));
    }

    @Test
    void centuryEncoding2000Lower_valid() {

    }

    @Test
    void centuryEncoding2000Upper_valid() {
        // can't be after today
    }

    @Test
    void centuryEncoding1900_invalid() {
        // before start of cpr register
    }

    @Test
    void centuryEncoding2000_invalid() {

    }

}