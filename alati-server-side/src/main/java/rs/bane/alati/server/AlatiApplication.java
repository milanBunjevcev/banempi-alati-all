package rs.bane.alati.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class AlatiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AlatiApplication.class, args);
	}

}
