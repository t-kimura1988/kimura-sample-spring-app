package daiku.domain.entity;

import daiku.domain.enums.PublishLevel;
import daiku.domain.enums.UpdatingFlg;
import daiku.domain.listener.TGoalArchiveListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = TGoalArchiveListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_goal_archives")
public class TGoalArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_goal_archive_id_seq")
    private Long id;
    private LocalDate archivesCreateDate;
    private Long goalId;
    private String thoughts;
    private UpdatingFlg updatingFlg;
    private PublishLevel publish;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
