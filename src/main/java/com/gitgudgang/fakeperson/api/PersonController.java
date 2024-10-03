package com.gitgudgang.fakeperson.api;

import com.gitgudgang.fakeperson.dto.PersonDTO;
import com.gitgudgang.fakeperson.service.PersonService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{dtoType}")
    public PersonDTO.PartialPersonData getPartialPersonData(@PathVariable String dtoType) {
        return personService.generatePartialPersonData(dtoType);
    }

    @GetMapping("/single")
    public PersonDTO.FullPersonDTO getSinglePerson() {
        return personService.generateFullPerson();
    }

    @GetMapping("/bulk")
    public List<PersonDTO.FullPersonDTO> getBulkPersons(@RequestParam(defaultValue = "10") int count) {
        return null;
    }
}
