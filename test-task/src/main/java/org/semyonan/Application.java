package org.semyonan;

import org.semyonan.parser.FileParser;
import org.semyonan.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.util.Pair;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EntityScan("org.semyonan.entities")
public class Application {

	@Value("${app.data-file-location}")
	private String dataFileLocation;

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void initializeData() throws FileNotFoundException {

		List<Pair<String, Integer>> list = FileParser.parse(dataFileLocation);
		list.forEach(entity -> { personService.save(entity.getFirst(), entity.getSecond()); });

	}

}
