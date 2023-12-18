package cm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="languages")
public class Language {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="language_id")
    private Integer id;

    @Column(name="language_name")
    private String name;

    @Column(name="preferred_language")
    private Boolean preferredLanguage;

    //  "The default constructor exists only for the sake of JPA. 
	//  You do not use it directly, so it is designated as protected"
	//  Source: https://spring.io/guides/gs/accessing-data-jpa/
    protected Language() {
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

    public Boolean getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Boolean preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
    

    
}
