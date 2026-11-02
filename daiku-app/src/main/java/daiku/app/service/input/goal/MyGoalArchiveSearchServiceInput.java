package daiku.app.service.input.goal;

import daiku.domain.model.param.GoalArchiveDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class MyGoalArchiveSearchServiceInput {
    Long accountId;
    LocalDate fromDate;
    LocalDate toDate;

    public GoalArchiveDaoParam toRepository() {
        return GoalArchiveDaoParam.builder()
                .accountId(accountId)
                .fromCreateDate(fromDate)
                .toCreateDate(toDate)
                .build();
    }
}
