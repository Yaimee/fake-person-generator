package com.gitgudgang.fakeperson.entity;

import com.gitgudgang.fakeperson.validator.ValidDanishCpr;
import com.gitgudgang.fakeperson.validator.ValidDanishPhoneNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;

    private String lastName;

    private String gender;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @ValidDanishCpr
    private String cpr;

    private String address;

    @ValidDanishPhoneNumber
    private int phoneNumber;
}

//TODO: Make converter for translating dob to valid danish cpr dob to avoid century problem
