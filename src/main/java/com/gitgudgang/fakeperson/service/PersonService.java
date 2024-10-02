package com.gitgudgang.fakeperson.service;


import com.gitgudgang.fakeperson.entity.Person;
import com.gitgudgang.fakeperson.entity.generator.PersonDataGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDataGenerator personDataGenerator = new PersonDataGenerator();

    public Person generateFullPerson() {
        return new Person();
    }

    public List<Person> generatePersonList() {
        // random int between 2 and 100

        // generate persons until list is full

        // return list of persons

        return null;
    }
}
