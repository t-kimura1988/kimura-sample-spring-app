package daiku.domain.model.param;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GoalDaoParam {
    private Long goalId;
    private Long accountId;
    private LocalDate fromCreateDate;
    private LocalDate toCreateDate;
    private LocalDate createDate;
    private Boolean olderThan;
    private int page;
}
