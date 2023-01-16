package cm.controllers;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cm.models.Project;
import cm.repositories.ProjectsRepository;

@RestController
public class ProjectController 
{
	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	ProjectsRepository repository;
	
	/**
	 * A method used to find all the project data stored in the database
	 * @return a list of Project objects
	 */
	@GetMapping("/projects")
	public List<Project> getProjects()
	{
		List<Project> list = repository.findAll();
		Collections.sort(list);
		return list;
	}
	
	/**
	 * A method that handles requests for the homepage
	 * 
	 * @return	A text to display on the homepage
	 */
	@GetMapping("/")
	public String index() {
		return "Index page. <br>Do you try to reach <a href=\"/projects\"> the projects </a> ?";
	}
	
}
