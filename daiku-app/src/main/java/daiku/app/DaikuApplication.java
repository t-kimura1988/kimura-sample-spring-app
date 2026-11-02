package daiku.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"daiku"})
public class DaikuApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaikuApplication.class, args);
	}

}
