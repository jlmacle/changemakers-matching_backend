package cm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.models.Project;

/**
 * Repository interface used to access the data related to the projects
 *
 */
public interface ProjectsRepository extends JpaRepository<Project, Integer>
{
	
}
