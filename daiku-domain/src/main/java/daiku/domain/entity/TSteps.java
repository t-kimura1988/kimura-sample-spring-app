package daiku.domain.entity;

import daiku.domain.listener.TStepsListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = TStepsListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_steps")
public class TSteps {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_steps_id_seq")
    private Long id;
    private Long accountId;
    private LocalDate createDate;
    private String body;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
