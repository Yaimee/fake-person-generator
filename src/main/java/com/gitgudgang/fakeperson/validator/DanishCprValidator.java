package com.gitgudgang.fakeperson.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DanishCprValidator implements ConstraintValidator<ValidDanishCpr, String> {
    private static final Pattern CPR_REGEX_PATTERN = Pattern.compile("^([0-9]{10})$");
    private static final LocalDate EARLIEST_VALID_DATE = LocalDate.of(1900, 1, 1);
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        var matcher = CPR_REGEX_PATTERN.matcher(value);
        if (!matcher.matches()) {
            return false;
        }

        var dateString = value.substring(0, 7);
        return isValidDate(dateString);
    }

    private boolean isValidDate(String dateString) {
        try {
            LocalDate birthDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("ddMMyy"));
            LocalDate adjustedBirthDate = adjustYearIfNeeded(birthDate);
            return adjustedBirthDate.isAfter(EARLIEST_VALID_DATE) &&
                    adjustedBirthDate.isBefore(LocalDate.now().plusDays(1));
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private LocalDate adjustYearIfNeeded(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            return date.minusYears(100);
        }
        return date;
    }
}

// TODO: Adjust constraints to project requirements