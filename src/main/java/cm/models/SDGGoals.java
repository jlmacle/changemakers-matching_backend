package cm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sdg_goals")
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
    
}
