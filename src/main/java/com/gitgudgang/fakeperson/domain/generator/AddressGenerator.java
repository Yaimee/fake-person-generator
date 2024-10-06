package com.gitgudgang.fakeperson.domain.generator;

import com.gitgudgang.fakeperson.domain.Address;
import com.gitgudgang.fakeperson.entity.PostalCodeTown;
import com.gitgudgang.fakeperson.repository.PostalCodeTownRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Service
public class AddressGenerator {
    private PostalCodeTownRepository postalCodeTownRepository;
    private final Faker faker = new Faker();
    private final Random rand = new Random();

    public Address generateAddress() {
        //var baseData = generateAddressBaseData().orElseThrow();
        //TODO:Get dummy postalCode and town from actual data source
        var street = generateStreetName();
        var houseNumber = generateHouseNumber();
        var floor = generateFloor();
        var door = generateDoor();
        var postalCode = faker.number().numberBetween(1000, 9999); //baseData.getPostalCode();
        var town = faker.gameOfThrones().city(); //baseData.getTown();

        return new Address(UUID.randomUUID(), street, houseNumber, floor, door, postalCode, town);
    }

    private Optional<PostalCodeTown> generateAddressBaseData() {
        var index = rand.nextInt(postalCodeTownRepository.findAll().size()); //TODO: Check for if db is empty
        var id = postalCodeTownRepository.getAllUUIDs().get(index);
        return postalCodeTownRepository.findById(id);
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
