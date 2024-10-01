package com.gitgudgang.fakeperson.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DanishCprValidator implements ConstraintValidator<ValidDanishCpr, String> {
    private static final Pattern CPR_REGEX_PATTERN = Pattern.compile("^([0-9]{10})$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        var matcher = CPR_REGEX_PATTERN.matcher(value);
        if (!matcher.matches()) {
            return false;
        }

        String dateString = matcher.group(1);
        return isValidDate(dateString);
    }

    private boolean isValidDate(String dateString) {
        try {
            // Parse as ddMMyy
            LocalDate.parse(dateString, DateTimeFormatter.ofPattern("ddMMyy"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

// TODO: Adjust constraints to project requirements