package com.gitgudgang.fakeperson.entity;

import com.gitgudgang.fakeperson.repository.PostalCodeTownRepository;
import com.github.javafaker.Faker;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Address {
    private PostalCodeTownRepository postalCodeTownRepository;
    private final Faker faker = new Faker();

    private String postalCode;
    private String town;
    private String street = faker.regexify("[a-zA-Z]+]");
    private int houseNumber;
    private String floor;
    private String door;
}
