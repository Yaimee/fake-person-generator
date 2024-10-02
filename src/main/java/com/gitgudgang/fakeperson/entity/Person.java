package com.gitgudgang.fakeperson.entity;

import com.gitgudgang.fakeperson.validator.ValidDanishCpr;
import com.gitgudgang.fakeperson.validator.ValidDanishPhoneNumber;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Getter
@Setter
public class Person {

    private String firstName;

    private String lastName;

    private String gender;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @ValidDanishCpr
    private String cpr;

    private Address address;

    @ValidDanishPhoneNumber
    private int phoneNumber;
}

//TODO: Make converter for translating dob to valid danish cpr dob to avoid century problem
