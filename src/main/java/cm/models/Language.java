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
@Table(name="languages")
@Getter
@Setter
@NoArgsConstructor // Instead of the default constructor, we use Lombok to generate a no-args constructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Language {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="language_id")
    private Integer languageId;

    @Column(name="language_name")
    private String name;

    @Column(name="preferred_language")
    private Boolean preferredLanguage;

    @Version // Hibernate will automatically increment this field on update
    private Integer version; 

    public Language(Integer id, String name, Integer version) {
        this.languageId = id;
        this.name = name;
        this.version = version;
    }
}
