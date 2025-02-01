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
@Table(name="sdg_goals")
@Getter
@Setter
@NoArgsConstructor // Instead of the default constructor, we use Lombok to generate a no-args constructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SDGGoals {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="sdg_goal_id")
    private Integer sdgGoalId;

    @Column(name="sdg_goal_name")
    private String name;

    @Column(name="sdg_number")
    private Integer number;

    @Column(name="sdg_subcategory")
    private String subcategory;

    @Version // Hibernate will automatically increment this field on update
    private Integer version; 
}
