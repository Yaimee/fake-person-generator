package com.gitgudgang.fakeperson.service;

import com.gitgudgang.fakeperson.domain.Address;
import com.gitgudgang.fakeperson.domain.Person;
import com.gitgudgang.fakeperson.domain.generator.PersonDataGenerator;
import com.gitgudgang.fakeperson.dto.PersonDtoType;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.gitgudgang.fakeperson.dto.PersonDTO.*;

@AllArgsConstructor
@Service
public class PersonService {
    private PersonDataGenerator dataGenerator;
    private ModelMapper modelMapper;

    public PersonService() {
        this.dataGenerator = new PersonDataGenerator();
        this.modelMapper = new ModelMapper();
    }

    public record PersonBaseData(String firstName, String lastName, String gender) {}

    private PersonBaseData generatePersonBaseData() {
        return dataGenerator.generatePersonBaseData();
    }

    private Person generateFullPerson() {
        PersonBaseData baseData = generatePersonBaseData();
        var dob = dataGenerator.generateDateOfBirth();

        Person person = new Person();
        person.setFirstName(baseData.firstName);
        person.setLastName(baseData.lastName);
        person.setGender(baseData.gender);
        person.setDateOfBirth(dob);
        person.setCpr(generateCpr(baseData, dob));
        person.setAddress(new Address()); //TODO: Make return actual address
        person.setPhoneNumber(dataGenerator.generatePhoneNumber());

        return person;
    }

    public PartialPersonData generatePersonData(String type) {
        return switch (PersonDtoType.valueOf(type.toUpperCase())) {
            case CPR_DTO -> modelMapper.map(generateFullPerson(), PartialPersonData.class);
            case FIRST_NAME_LAST_NAME_CPR_DTO -> modelMapper.map(generateFullPerson(), NameCprDTO.class);
            case FIRST_NAME_LAST_NAME_CPR_DATE_OF_BIRTH_DTO ->
                    modelMapper.map(generateFullPerson(), NameCprDobDTO.class);
            case CPR_FIRST_NAME_LAST_NAME_GENDER_DTO -> modelMapper.map(generateFullPerson(), CprNameGenderDTO.class);
            case CPR_FIRST_NAME_LAST_NAME_GENDER_DATE_OF_BIRTH_DTO ->
                    modelMapper.map(generateFullPerson(), CprNameGenderDobDTO.class);
            case ADDRESS_DTO -> modelMapper.map(dataGenerator.generateAddress(), AddressDTO.class);
            case PHONE_DTO -> modelMapper.map(dataGenerator.generatePhoneNumber(), PhoneDTO.class);
            default -> throw new IllegalArgumentException("Unsupported DTO type: " + type);
        };
    }

    public List<FullPersonDTO> generatePersonList() {
        // random int between 2 and 100

        // generateFullPerson() until list is full

        // Convert List of Persons to DTOs

        // Return dto list

        return null;
    }

    private String generateCpr(PersonBaseData baseData, LocalDate dob) {
        return dataGenerator.generateCpr(baseData.gender(), dob);
    }
}
