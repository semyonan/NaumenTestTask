package org.semyonan.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long id;
    private String name;
    private Integer age;
    private Integer count;

    public PersonDto(String name, int age, int count){
        this.name = name;
        this.age = age;
        this.count = count;
    }
}
