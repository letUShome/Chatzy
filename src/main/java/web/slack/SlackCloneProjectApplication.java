package web.slack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class SlackCloneProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlackCloneProjectApplication.class, args);
	}

}
