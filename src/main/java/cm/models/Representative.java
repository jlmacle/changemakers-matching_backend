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
@Table(name="representatives")
@Getter
@Setter
@NoArgsConstructor // Instead of the default constructor, we use Lombok to generate a no-args constructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Representative {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="representative_id")
    private Integer id;

    @Column(name="representative_username")
    private String username;

    @Column(name="representative_password")
    private String password;

    @Column(name="representative_email")
    private String email;

    @Version // Hibernate will automatically increment this field on update
    private Integer version; 

    public Representative (String username, String password){
        this.username = username;
        this.password = password;        
    }
}
