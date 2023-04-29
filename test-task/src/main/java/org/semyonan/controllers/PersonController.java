package org.semyonan.controllers;

import org.semyonan.dto.PersonDto;
import org.semyonan.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getage")
    public Integer getName(@RequestParam String name) {
        personService.update(name);
        return personService.getByName(name);
    }

    @GetMapping("/getmaxaged")
    @ResponseBody
    public PersonDto getMaxAged(){
        return personService.getMaxAge();
    }

    @GetMapping("/persons")
    public Iterable<PersonDto> greeting() {
        return personService.getAll();
    }
}
