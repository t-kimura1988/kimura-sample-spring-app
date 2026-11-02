package daiku.domain.model.param;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GoalArchiveDaoParam {
    private Long archiveId;
    private Long goalId;
    private Long accountId;
    private LocalDate archiveCreateDate;
    private LocalDate fromCreateDate;
    private LocalDate toCreateDate;
    private int page;
}
