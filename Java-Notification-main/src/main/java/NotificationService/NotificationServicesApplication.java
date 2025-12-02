package NotificationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NotificationServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServicesApplication.class, args);
	}

}
