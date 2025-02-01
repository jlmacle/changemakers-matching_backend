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
@Table(name="project_needs")
@Getter
@Setter
@NoArgsConstructor // Instead of the default constructor, we use Lombok to generate a no-args constructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProjectNeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_need_id")
    private Integer projectNeedId;

    @Column(name="project_need_name")
    private String name;

    @Version // Hibernate will automatically increment this field on update
    private Integer version; 
   
}
