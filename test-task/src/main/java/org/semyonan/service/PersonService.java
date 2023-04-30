package org.semyonan.service;

import org.modelmapper.ModelMapper;
import org.semyonan.dto.PersonDto;
import org.semyonan.entities.Person;
import org.semyonan.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {

    static final int MAX_AGE = 100;

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private RemoteDataService remoteDataService;

    @Autowired
    public PersonService(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    // Add new record
    public void save(String name, int age) {
        var personDto = new PersonDto();
        personDto.setName(name);
        personDto.setAge(age);
        personDto.setCount(0);

        personRepository.save(modelMapper.map(personDto, Person.class));
    }

    public PersonDto getMaxAged() {
        return modelMapper.map(personRepository.findMaxAged(), PersonDto.class);
    }

    public List<PersonDto> getAll() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map((person) -> modelMapper.map(person, PersonDto.class))
                .toList();//collect(Collectors.toList());
    }

    public Integer getAgeByName(String name) {
        var result = personRepository.findAllAgeByName(name);

        if (result == null) {
            result = remoteDataService.getAgeByName(name);
        }

        if (result == null) {
            result = calculateAgeByName(name);
        }

        return result;
    }

    private int calculateAgeByName(String name) {
        int age = 0;
        for(int i = 0; i < name.length(); i++){
            age+=name.charAt(i);
        }
        return age % MAX_AGE;
    }

    public void update(String name) {
        var result = personRepository.findByName(name);
        if (result != null) {
            personRepository.save(new Person(result.getId(), result.getName(), result.getAge(), result.getCount()+1) );
        }
    }
}
