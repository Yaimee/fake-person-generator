package com.gitgudgang.fakeperson.service;

import com.gitgudgang.fakeperson.domain.Person;
import com.gitgudgang.fakeperson.domain.generator.AddressGenerator;
import com.gitgudgang.fakeperson.domain.generator.PersonDataGenerator;
import com.gitgudgang.fakeperson.domain.generator.PhoneNumberGenerator;
import com.gitgudgang.fakeperson.dto.PersonDtoType;
import com.gitgudgang.fakeperson.entity.NameGender;
import com.gitgudgang.fakeperson.exception.EndpointNotFoundException;
import com.gitgudgang.fakeperson.mapper.PersonMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.gitgudgang.fakeperson.dto.PersonDTO.FullPersonDTO;
import static com.gitgudgang.fakeperson.dto.PersonDTO.PartialPersonData;

@Getter
@Setter
@Service
public class PersonService {
    private PersonDataGenerator personGenerator;
    private AddressGenerator addressGenerator;
    private PhoneNumberGenerator phoneNumberGenerator;
    private PersonMapper personMapper;

    @Autowired
    public PersonService(PersonDataGenerator personGenerator, AddressGenerator addressGenerator, PhoneNumberGenerator phoneNumberGenerator, PersonMapper personMapper) {
        this.personGenerator = personGenerator;
        this.addressGenerator = addressGenerator;
        this.phoneNumberGenerator = phoneNumberGenerator;
        this.personMapper = personMapper;
    }

    private Person generateFullPerson() {
        NameGender nameGender = personGenerator.generatePersonBaseData().orElseThrow();
        var dob = personGenerator.generateDateOfBirth();

        Person person = new Person();
        person.setFirstName(nameGender.getFirstName());
        person.setLastName(nameGender.getLastName());
        person.setGender(nameGender.getGender());
        person.setDateOfBirth(dob);
        person.setCpr(personGenerator.generateCpr(nameGender.getGender(), dob));
        person.setAddress(addressGenerator.generateAddress());
        person.setPhoneNumber(phoneNumberGenerator.generatePhoneNumber()); //TODO: Make return actual phone number

        return person;
    }

    public PartialPersonData generatePersonData(String type) {
        return switch (matchEndpoint(type)) {
            case CPR_DTO -> personMapper.personToCprDTO(generateFullPerson());
            case FIRST_NAME_LAST_NAME_CPR_DTO -> personMapper.personToNameCprDTO(generateFullPerson());
            case FIRST_NAME_LAST_NAME_CPR_DATE_OF_BIRTH_DTO -> personMapper.personToNameCprDobDTO(generateFullPerson());
            case CPR_FIRST_NAME_LAST_NAME_GENDER_DTO -> personMapper.personToCprNameGenderDTO(generateFullPerson());
            case CPR_FIRST_NAME_LAST_NAME_GENDER_DATE_OF_BIRTH_DTO ->
                    personMapper.personToCprNameGenderDobDTO(generateFullPerson());
            case ADDRESS_DTO -> personMapper.addressToAddressDTO(generateFullPerson().getAddress());
            case PHONE_DTO -> personMapper.personToPhoneDTO(generateFullPerson());
            case SINGLE_PERSON_DTO -> personMapper.personToFullPersonDTO(generateFullPerson());
            default -> throw new EndpointNotFoundException("Unsupported DTO type: " + type);
        };
    }

    private static PersonDtoType matchEndpoint(String type) {
        try {
            return PersonDtoType.fromString(type.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new EndpointNotFoundException("Not Found", e);
        }
    }

    public List<FullPersonDTO> generatePersonList() {
        var index = new Random().nextInt(2, 100);
        var persons = new ArrayList<Person>();

        for (int i = 0; i < index; i++) {
            persons.add(generateFullPerson());
        }
        return personMapper.personToFullPersonDTOList(persons);
    }
}
