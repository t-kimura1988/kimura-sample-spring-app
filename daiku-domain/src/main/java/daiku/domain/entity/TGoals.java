package daiku.domain.entity;

import daiku.domain.listener.TGoalsListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = TGoalsListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_goals")
public class TGoals {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_goals_id_seq")
    private Long id;
    private LocalDate createDate;
    private Long accountId;
    private String title;
    private String purpose;
    private String aim;
    private LocalDate dueDate;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
