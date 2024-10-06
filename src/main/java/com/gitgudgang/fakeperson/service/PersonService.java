package com.gitgudgang.fakeperson.service;

import com.gitgudgang.fakeperson.domain.Address;
import com.gitgudgang.fakeperson.domain.Person;
import com.gitgudgang.fakeperson.domain.generator.PersonDataGenerator;
import com.gitgudgang.fakeperson.dto.PersonDtoType;
import com.gitgudgang.fakeperson.entity.NameGender;
import com.gitgudgang.fakeperson.mapper.PersonMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.gitgudgang.fakeperson.dto.PersonDTO.FullPersonDTO;
import static com.gitgudgang.fakeperson.dto.PersonDTO.PartialPersonData;

@Getter
@Setter
@Service
public class PersonService {
    private PersonDataGenerator dataGenerator;
    private PersonMapper personMapper;

    @Autowired
    public PersonService(PersonDataGenerator dataGenerator, PersonMapper personMapper) {
        this.dataGenerator = dataGenerator;
        this.personMapper = personMapper;
    }

    private Person generateFullPerson() {
        NameGender nameGender = dataGenerator.generatePersonBaseData().orElseThrow();
        var dob = dataGenerator.generateDateOfBirth();

        Person person = new Person();
        person.setFirstName(nameGender.getFirstName());
        person.setLastName(nameGender.getLastName());
        person.setGender(nameGender.getGender());
        person.setDateOfBirth(dob);
        person.setCpr(generateCpr(nameGender.getGender(), dob));
        person.setAddress(new Address()); //TODO: Make return actual address
        person.setPhoneNumber(dataGenerator.generatePhoneNumber());

        return person;
    }

    public PartialPersonData generatePersonData(String type) {
        return switch (PersonDtoType.fromString(type.toLowerCase())) {
            case CPR_DTO -> personMapper.personToCprDTO(generateFullPerson());
            case FIRST_NAME_LAST_NAME_CPR_DTO -> personMapper.personToNameCprDTO(generateFullPerson());
            case FIRST_NAME_LAST_NAME_CPR_DATE_OF_BIRTH_DTO -> personMapper.personToNameCprDobDTO(generateFullPerson());
            case CPR_FIRST_NAME_LAST_NAME_GENDER_DTO -> personMapper.personToCprNameGenderDTO(generateFullPerson());
            case CPR_FIRST_NAME_LAST_NAME_GENDER_DATE_OF_BIRTH_DTO ->
                    personMapper.personToCprNameGenderDobDTO(generateFullPerson());
            case ADDRESS_DTO -> personMapper.addressToAddressDTO(dataGenerator.generateAddress());
            case PHONE_DTO -> personMapper.personToPhoneDTO(generateFullPerson());
            case SINGLE_PERSON_DTO -> personMapper.personToFullPersonDTO(generateFullPerson());
            default -> throw new IllegalArgumentException("Unsupported DTO type: " + type);
        };
    }

    public List<FullPersonDTO> generatePersonList() {
        // random int between 2 and 100
        var index = new Random().nextInt(2, 100);
        var persons = new ArrayList<Person>();

        // generateFullPerson() and map to dto until list is full
        for (int i = 2; i < index; i++) {
            persons.add(generateFullPerson());
        }
        return personMapper.personToFullPersonDTOList(persons);
    }

    private String generateCpr(String gender, LocalDate dob) {
        return dataGenerator.generateCpr(gender, dob);
    }
}
