package pl.devzyra.todospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class TodoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoSpringApplication.class, args);
	}

}
