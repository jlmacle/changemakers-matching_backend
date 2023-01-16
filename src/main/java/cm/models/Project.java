package cm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="project")
public class Project implements Comparable<Object>
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private Integer id;
	
	@Column(name="project_name")
	private String name;
	
	//  "The default constructor exists only for the sake of JPA. 
	//  You do not use it directly, so it is designated as protected"
	//  Source: https://spring.io/guides/gs/accessing-data-jpa/
	protected Project() {
		super();
	}

	public Project(Integer projectId, String projectName) {
		super();
		this.id = projectId;
		this.name = projectName;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setId(Integer projectId) {
		this.id = projectId;
	}

	public void setName(String projectName) {
		this.name = projectName;
	}

	public int compareTo(Object object)
	{
		return this.getName().compareTo( ((Project)object).getName());
	}
	
	
	@Override
	public boolean equals(Object object)
	{
		if (object == null) {return false;}

		if (object.getClass() != Project.class) {return false;}

		return this.getId().equals(((Project)object).getId()) && this.getName().equals(((Project)object).getName());
	}

	@Override
	public int hashCode()
	{
		return this.getId().hashCode() + 1000000*this.getName().hashCode();
	}
	

}
