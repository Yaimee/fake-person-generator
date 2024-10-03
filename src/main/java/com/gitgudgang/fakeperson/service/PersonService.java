package com.gitgudgang.fakeperson.service;

import com.gitgudgang.fakeperson.dto.PersonDTO;
import com.gitgudgang.fakeperson.dto.PersonDtoType;
import com.gitgudgang.fakeperson.entity.generator.PersonDataGenerator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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

    public PersonDTO.FullPersonDTO generateFullPerson() {
        PersonBaseData baseData = generatePersonBaseData();
        return new PersonDTO.FullPersonDTO(
                generateCpr(baseData),
                baseData.firstName(),
                baseData.lastName(),
                baseData.gender(),
                dataGenerator.generateDateOfBirth(),
                modelMapper.map(dataGenerator.generateAddress(), PersonDTO.AddressDTO.class),
                dataGenerator.generatePhoneNumber()
        );
    }

    public PersonDTO.PartialPersonData generatePartialPersonData(String type) {
        return switch (PersonDtoType.valueOf(type.toUpperCase())) {
            case CPR_DTO -> {
                PersonBaseData baseData = generatePersonBaseData();
                yield new PersonDTO.CprDTO(generateCpr(baseData));
            }
            case FIRST_NAME_LAST_NAME_CPR_DTO -> {
                PersonBaseData baseData = generatePersonBaseData();
                yield new PersonDTO.NameCprDTO(
                        baseData.firstName(),
                        baseData.lastName(),
                        generateCpr(baseData)
                );
            }
            case FIRST_NAME_LAST_NAME_CPR_DATE_OF_BIRTH_DTO -> {
                PersonBaseData baseData = generatePersonBaseData();
                LocalDate dob = dataGenerator.generateDateOfBirth();
                yield new PersonDTO.NameCprDobDTO(
                        baseData.firstName(),
                        baseData.lastName(),
                        generateCpr(baseData),
                        dob
                );
            }
            case CPR_FIRST_NAME_LAST_NAME_GENDER_DTO -> {
                PersonBaseData baseData = generatePersonBaseData();
                yield new PersonDTO.CprNameGenderDTO(
                        generateCpr(baseData),
                        baseData.firstName(),
                        baseData.lastName(),
                        baseData.gender()
                );
            }
            case CPR_FIRST_NAME_LAST_NAME_GENDER_DATE_OF_BIRTH_DTO -> {
                PersonBaseData baseData = generatePersonBaseData();
                LocalDate dob = dataGenerator.generateDateOfBirth();
                yield new PersonDTO.CprNameGenderDobDTO(
                        generateCpr(baseData),
                        baseData.firstName(),
                        baseData.lastName(),
                        baseData.gender(),
                        dob
                );
            }
            case ADDRESS_DTO -> modelMapper.map(dataGenerator.generateAddress(), PersonDTO.AddressDTO.class);
            case PHONE_DTO -> new PersonDTO.PhoneDTO(dataGenerator.generatePhoneNumber());
            default -> throw new IllegalArgumentException("Unsupported DTO type: " + type);
        };
    }

    public List<PersonDTO.FullPersonDTO> generatePersonList() {
        // random int between 2 and 100

        // generateFullPerson() until list is full

        // Convert List of Persons to DTOs

        // Return dto list

        return null;
    }

    private String generateCpr(PersonBaseData baseData) {
        return dataGenerator.generateCpr(baseData.gender());
    }
}
