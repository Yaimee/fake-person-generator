package com.gitgudgang.fakeperson.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DanishPhoneNumberValidator implements ConstraintValidator<ValidDanishPhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {

        //TODO: Add danish phone number validation according to assignment specification
        return true;
    }
}


