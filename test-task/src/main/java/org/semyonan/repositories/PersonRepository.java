package org.semyonan.repositories;

import org.semyonan.entities.Person;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Iterator;


@Repository
@ComponentScan("entities.Person")
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT r.age FROM Person r WHERE r.name = :name")
    public Integer findAllAgeByName(@Param("name") String name);
    @Query("SELECT p FROM Person as p ORDER BY p.age DESC LIMIT 1")
    public Person findMaxAged();
    public Integer getIdByName(String name);
    Person findByName(String name);
}
