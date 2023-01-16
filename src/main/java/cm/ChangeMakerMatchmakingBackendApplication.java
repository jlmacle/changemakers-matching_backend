package cm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cm.models.Project;
import cm.repositories.ProjectsRepository;

/**
 * The class to run as a SpringBoot app to start the backend service
 */
@SpringBootApplication
public class ChangeMakerMatchmakingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChangeMakerMatchmakingBackendApplication.class, args);
	}

    
   /**
    * Returns  a CommandLineRunner interface
    * Used to initialize the database with the data related to a test project.
    * @param  repository  an instance of the class ProjectRepository
    * @return a CommandLineRunner interface
    */
	@Bean
    CommandLineRunner createATestProjectAtStartup(ProjectsRepository repository) {
        return args -> {
            Project testProject = new Project(1, "test project");
            repository.save(testProject);
        };
    }

}
