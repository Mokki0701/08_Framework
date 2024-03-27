package bradyes.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BradysCoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BradysCoffeeApplication.class, args);
	}

}
