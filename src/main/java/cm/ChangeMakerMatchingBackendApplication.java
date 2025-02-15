package cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The class to run as a Spring Boot application to start the backend service.
 */
@SpringBootApplication
public class ChangeMakerMatchingBackendApplication {

    /**
     * The main method to start the Spring Boot application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ChangeMakerMatchingBackendApplication.class, args);
    }

}
