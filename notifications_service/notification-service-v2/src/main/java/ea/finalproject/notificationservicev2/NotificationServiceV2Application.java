package ea.finalproject.notificationservicev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotificationServiceV2Application {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceV2Application.class, args);
    }

}
