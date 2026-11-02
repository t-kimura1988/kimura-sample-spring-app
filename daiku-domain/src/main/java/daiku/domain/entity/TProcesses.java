package daiku.domain.entity;

import daiku.domain.listener.TProcessesListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = TProcessesListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_processes")
public class TProcesses {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_processes_id_seq")
    private Long id;
    private Long goalId;
    private Long accountId;
    private LocalDate goalCreateDate;
    private String title;
    private String body;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
