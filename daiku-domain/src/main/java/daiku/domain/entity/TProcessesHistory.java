package daiku.domain.entity;

import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import daiku.domain.listener.TProcessesHistoryListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = TProcessesHistoryListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_processes_history")
public class TProcessesHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_processes_history_id_seq")
    private Long id;
    private Long processId;
    private Long accountId;
    private LocalDate goalCreateDate;
    private ProcessPriority beforePriority;
    private ProcessPriority priority;
    private ProcessStatus beforeProcessStatus;
    private ProcessStatus processStatus;
    private LocalDate processStartDate;
    private LocalDate beforeProcessStartDate;
    private LocalDate processEndDate;
    private LocalDate beforeProcessEndDate;
    private String comment;
    private String beforeTitle;
    private String beforeBody;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
