package org.semyonan;

import org.modelmapper.ModelMapper;
import org.semyonan.entities.Person;
import org.semyonan.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    public void save(String name, int age) {
        var personDto = new PersonDto();
        personDto.setName(name);
        personDto.setAge(age);
        personDto.setCount(0);

        personRepository.save(modelMapper.map(personDto, Person.class));
    }

    public PersonDto getMaxAge() {
        return modelMapper.map(personRepository.findMaxAged(), PersonDto.class);
    }

    public List<PersonDto> getAll() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map((person) -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());
    }

    public Integer getByName(String name) {
        var result = personRepository.findAllAgeByName(name);

        if (result == null) {
            int age = 0;
            for(int i = 0; i < name.length(); i++){
                age+=name.charAt(i);
            }
            return age % 100;
        }

        return result;
    }

    public void update(String name) {
        var result = personRepository.findByName(name);
        if (result != null) {
            personRepository.save(new Person(result.getId(), result.getName(), result.getAge(), result.getCount()+1) );
        }
    }
}
