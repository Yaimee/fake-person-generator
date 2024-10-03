package com.gitgudgang.fakeperson.domain.generator;

import com.gitgudgang.fakeperson.domain.Address;
import com.gitgudgang.fakeperson.service.PersonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class PersonDataGenerator {
    //private NameGenderRepository nameGenderRepository;

    private final Random random = new Random();

    public String generateCpr(String gender, LocalDate dob) {
        var dateString = dob.format(DateTimeFormatter.ofPattern("ddMMyy"));
        StringBuilder cpr = new StringBuilder(dateString);
        for (int i = 0; i < 3; i++) {
            cpr.append(random.nextInt(10));
        }
        var lastDigit = gender.equalsIgnoreCase("female") ? random.nextInt(5) * 2 : random.nextInt(5) * 2 + 1;
        return cpr.append(lastDigit).toString();
    }

    public LocalDate generateDateOfBirth() {
        LocalDate now = LocalDate.now();
        var minDay = LocalDate.of(1910, 1, 1).toEpochDay();
        var maxDay = now.toEpochDay();
        return LocalDate.ofEpochDay(minDay + random.nextLong(maxDay - minDay + 1));
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
