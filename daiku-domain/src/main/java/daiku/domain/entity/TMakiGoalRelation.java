package daiku.domain.entity;

import daiku.domain.listener.TMakiGoalRelationListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = TMakiGoalRelationListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_maki_goal_relation")
public class TMakiGoalRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_maki_goal_relation_id_seq")
    private Long id;
    private Long makiId;
    private Long goalId;
    private LocalDate goalCreateDate;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private Long sortNum;
}
