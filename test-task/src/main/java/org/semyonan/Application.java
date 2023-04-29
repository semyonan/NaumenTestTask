package org.semyonan;

import org.semyonan.parser.FileParser;
import org.semyonan.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EntityScan("org.semyonan.entities")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(PersonService personService) {
		return args -> {
			new FileParser().parse(Arrays.stream(args).iterator().next()).forEach(entity -> {
				personService.save(entity.getFirst(), entity.getSecond());
			});
		};
	}
}
