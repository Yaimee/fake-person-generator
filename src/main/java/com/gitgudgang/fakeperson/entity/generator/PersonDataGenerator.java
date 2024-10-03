package com.gitgudgang.fakeperson.entity.generator;

import com.gitgudgang.fakeperson.entity.Address;
import com.gitgudgang.fakeperson.repository.NameGenderRepository;
import com.gitgudgang.fakeperson.repository.PostalCodeTownRepository;
import com.gitgudgang.fakeperson.service.PersonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonDataGenerator {
    private NameGenderRepository nameGenderRepository;
    private PostalCodeTownRepository postalCodeTownRepository;

    public String generateCpr(String gender) {
        return null;
    }

    public LocalDate generateDateOfBirth() {
        return null;
    }

    public int generatePhoneNumber() {
        return 0;
    }

    public Address generateAddress() {
        return null;
    }

    public PersonService.PersonBaseData generatePersonBaseData() {
        return null;
    }
}
