package org.semyonan.controllers;

import org.semyonan.dto.PersonDto;
import org.semyonan.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getage")
    public Integer getAgeByName(@RequestParam String name) {
        personService.update(name);
        return personService.getAgeByName(name);
    }

    @GetMapping("/getmaxaged")
    @ResponseBody
    public PersonDto getMaxAged(){
        return personService.getMaxAged();
    }

    @GetMapping(value={"/", "/persons"})
    public Iterable<PersonDto> getPersons() {
        return personService.getAll();
    }
}
