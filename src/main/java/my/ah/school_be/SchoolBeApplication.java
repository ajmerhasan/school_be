package my.ah.school_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SchoolBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolBeApplication.class, args);
	}

}
