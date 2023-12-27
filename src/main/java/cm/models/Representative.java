package cm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="representatives")
public class Representative {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="represntative_id")
    private Integer id;

    @Column(name="representative_username")
    private String username;

    @Column(name="representative_password")
    private String password;

    @Column(name="representative_email")
    private String email;

    //  "The default constructor exists only for the sake of JPA.
    //  You do not use it directly, so it is designated as protected"
	//  Source: https://spring.io/guides/gs/accessing-data-jpa/

    protected Representative() {
        super();
    }

    public static Representative createRepresentative(String username, String password){
        Representative representative = new Representative();
        representative.setUsername(username);
        representative.setPassword(password);
        return representative;
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

    @Override
    public String toString() {
        return "Representative [ username=" + username+ "]";
    }

    
    

}
