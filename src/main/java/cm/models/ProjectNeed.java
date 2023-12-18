package cm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="project_needs")
public class ProjectNeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_need_id")
    private Integer id;

    @Column(name="project_need_name")
    private String name;

    protected ProjectNeed() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    
}
