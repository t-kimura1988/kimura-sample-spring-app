package daiku.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"daiku"})
@EnableBatchProcessing
@EnableScheduling
public class DaikuBatchApplication {
	public static void main(String[] args) {
		SpringApplication.run(DaikuBatchApplication.class, args);
	}

}
