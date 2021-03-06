package css.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CsserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsserviceApplication.class, args);
	}
}
