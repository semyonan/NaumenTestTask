package org.semyonan.config;
import org.modelmapper.ModelMapper;
import org.semyonan.parser.FileParser;
import org.semyonan.service.PersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@Configuration
@EnableJpaRepositories(basePackages = "org.semyonan.repositories")
public class Config {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
