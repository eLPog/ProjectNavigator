package wsb2023.pogorzelski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@SpringBootApplication
@EnableGlobalAuthentication
public class PogorzelskiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PogorzelskiApplication.class, args);
	}

}
