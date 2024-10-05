package com.gitgudgang.fakeperson.domain.generator;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

@Service
public class AddressGenerator {


    private final Faker faker = new Faker();

    private String generateStreetName() {
        return null;
    }

    private String generateHouseNumber() {
        return null;
    }
    private String generateApartment() {
        return null;
    }
}
