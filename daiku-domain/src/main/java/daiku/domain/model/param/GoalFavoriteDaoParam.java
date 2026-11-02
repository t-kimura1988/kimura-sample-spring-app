package daiku.domain.model.param;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GoalFavoriteDaoParam {
    private Long id;
    private Long goalId;
    private Long accountId;
    private LocalDate favoriteCreateDateFrom;
    private LocalDate favoriteCreateDateTo;
    private int page;
}
