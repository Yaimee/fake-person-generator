package com.gitgudgang.fakeperson.api;

import com.gitgudgang.fakeperson.dto.PersonDTOs;
import com.gitgudgang.fakeperson.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper = new ModelMapper();


    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{dtoType}")
    public Object getPartialPersonData(@PathVariable String dtoType) {
        return null;
    }

    @GetMapping("/single")
    public PersonDTOs.PersonDto getSinglePerson() {
        var person = personService.generateFullPerson();
        return modelMapper.map(person, PersonDTOs.PersonDto.class);
    }

    @GetMapping("/bulk")
    public List<PersonDTOs.PersonDto> getBulkPersons(@RequestParam(defaultValue = "10") int count) {
        return null;
    }
}
