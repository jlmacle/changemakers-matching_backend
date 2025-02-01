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
@Table(name="contributors")
@Getter
@Setter
@NoArgsConstructor // Instead of the default constructor, we use Lombok to generate a no-args constructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
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

    @Version // Hibernate will automatically increment this field on update
    private Integer version; 

    public Contributor(String username, String password){
        this.username = username;
        this.password = password;
    }
   
}
