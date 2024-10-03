package com.gitgudgang.fakeperson.domain;

import com.gitgudgang.fakeperson.validator.ValidDanishCpr;
import com.gitgudgang.fakeperson.validator.ValidDanishPhoneNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Past;
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

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @ValidDanishCpr
    private String cpr;

    @OneToOne
    private Address address;

    @ValidDanishPhoneNumber
    private int phoneNumber;
}