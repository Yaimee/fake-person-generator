package com.gitgudgang.fakeperson.api;

import com.gitgudgang.fakeperson.dto.PersonDTO.FullPersonDTO;
import com.gitgudgang.fakeperson.dto.PersonDTO.PartialPersonData;
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
    public PartialPersonData getPartialPersonData(@PathVariable String dtoType) {
        return personService.generatePersonData(dtoType);
    }

    @GetMapping("/bulk")
    public List<FullPersonDTO> getBulkPersons(@RequestParam(defaultValue = "10") int count) {
        return personService.generatePersonList();
    }
}
