package com.gitgudgang.fakeperson.validator;

import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DanishCprValidator.class)
public @interface ValidDanishCpr {
    String message() default "Invalid Danish CPR";
}

