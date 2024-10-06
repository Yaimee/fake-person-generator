package com.gitgudgang.fakeperson.domain.generator;

import com.gitgudgang.fakeperson.domain.Address;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class AddressGenerator {


    private final Faker faker = new Faker();
    private final Random rand = new Random();

    public Address generateAddress() {
        //TODO: Refine by making floor and door optional
        var street = generateStreetName();
        var houseNumber = generateHouseNumber();
        var floor = generateFloor();
        var door = generateDoor();
        var postalCode = rand.nextInt(1, 9999);
        var town = faker.address().city();

        return new Address(UUID.randomUUID(), street, houseNumber, floor, door, postalCode, town);
    }

    private String generateStreetName() {
        return faker.address().streetName();
    }

    private int generateHouseNumber() {
        return faker.number().numberBetween(1, 500);
    }

    private String generateFloor() {
        var ground = "st";
        var maxFloors = 43; // Tallest building in DK as of 2024-10-06
        var index = faker.number().numberBetween(0, maxFloors);
        if (index == 0) {
            return ground;
        }
        return String.valueOf(index);
    }

    private String generateDoor() {
        var aptPlacements = List.of("tv.", "th.", "mf.");
        return aptPlacements.get(rand.nextInt(3));
    }
}
