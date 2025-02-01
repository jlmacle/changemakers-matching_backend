package cm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor // Instead of the default constructor, we use Lombok to generate a no-args constructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Project implements Comparable<Object>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "project_name", nullable = false)
    private String name;

    @Column(name = "mission_statement", length = 500)
    private String missionStatement;

    @Column(name = "project_description", length = 1000)
    private String description;

	@Version
    private Integer version; // Optimistic locking

	public Project(Integer projectId, String projectName, Integer version) {
		super();
		this.projectId = projectId;
		this.name = projectName;
		this.version = version;
	}

	public int compareTo(Object object)
	{
		return this.getName().compareTo( ((Project)object).getName());
	}
}