package org.semyonan.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;
    @Column(name = "AGE", nullable = false)
    private Integer age;
    @Column(name = "COUNT", nullable = false)
    private Integer count;

    public Person(String name, int age, int count){
        this.name = name;
        this.age = age;
        this.count = count;
    }
}
