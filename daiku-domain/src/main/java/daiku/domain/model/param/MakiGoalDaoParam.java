package daiku.domain.model.param;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MakiGoalDaoParam {
    private Long makiId;
    private Long goalId;
    private Long accountId;
    private LocalDate goalCreateDate;
}
