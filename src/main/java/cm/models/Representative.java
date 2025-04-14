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

/**
 * Represents a representative for a project.
 */
@Entity
@Table(name="representatives")
@Getter
@Setter
@NoArgsConstructor // Instead of the default constructor, we use Lombok to generate a no-args constructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Representative {
    
    /**
     * The unique identifier for the representative.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="representative_id")
    private Integer id;

    /**
     * The username of the representative.
     */
    @Column(name="representative_username")
    private String username;

    /**
     * The password of the representative.
     */
    @Column(name="representative_password")
    private String password;

    /**
     * The email of the representative.
     */
    @Column(name="representative_email")
    private String email;

    @Column(name="project_name")
    private String projectName;


    /**
    * A field useful for Hibernate.
     * Hibernate will automatically increment this field on update.
     */
    @Version
    private Integer version; 

    /**
     * Constructs a new Representative object with the specified username and password.
     *
     * @param username the username of the representative
     * @param password the password of the representative
     */
    public Representative (String username, String password){
        this.username = username;
        this.password = password;        
    }
}
