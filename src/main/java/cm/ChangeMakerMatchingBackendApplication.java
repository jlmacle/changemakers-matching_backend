package cm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cm.models.Language;
import cm.models.Project;
import cm.repositories.LanguagesRepository;
import cm.repositories.ProjectsRepository;

/**
 * The class to run as a SpringBoot app to start the backend service
 */
@SpringBootApplication
public class ChangeMakerMatchingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChangeMakerMatchingBackendApplication.class, args);
	}

    
   /**
    * Returns  a CommandLineRunner interface
    * Used to initialize the database with the data related to a test project.
    * @param  repository  an instance of the class ProjectRepository
    * @return a CommandLineRunner interface
    */
	@Bean
    CommandLineRunner populateDataAtStartup(ProjectsRepository projRepository,LanguagesRepository langRepository ) {
        return args -> {
            //Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect): [cm.models.Language#1]
            // To be investigated later
            /* Language english = new Language(1, "English", 1);
            langRepository.save(english);
            
            Project testProject = new Project(1, "test project", 1);
            projRepository.save(testProject); */

            
        };
    }

}
