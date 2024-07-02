package com.toDoApp.toDoBackend;

import com.toDoApp.toDoBackend.run.Location;
import com.toDoApp.toDoBackend.run.Run;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class ToDoBackendApplication {

	public static final Logger log = LoggerFactory.getLogger(ToDoBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ToDoBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args ->  {
			Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
			log.info("Run: " + run);
		};
	}
}

