package cm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="contributors")
public class Contributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contributor_id")
    private Integer id;

    @Column(name="contributor_username")
    private String username;

    @Column(name="contributor_password")
    private String password;

    @Column(name="contributor_email")
    private String email;

    @Column(name="contributor_firstname")
    private String firstname;

    @Column(name="contributor_lastname")
    private String lastname;

    //  "The default constructor exists only for the sake of JPA. 
	//  You do not use it directly, so it is designated as protected"
	//  Source: https://spring.io/guides/gs/accessing-data-jpa/
    protected Contributor() {
        super();
    }

    public static Contributor createContributor(String username, String password){
        Contributor contributor = new Contributor();
        contributor.setUsername(username);
        contributor.setPassword(password);
        return contributor;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstname() {
        return firstname;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Contributor [ username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }

    
    
}
