package daiku.app.service.input.goal;

import daiku.domain.model.param.GoalArchiveDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveSearchServiceInput {
    Long accountId;
    LocalDate fromDate;
    LocalDate toDate;
    int page;

    public GoalArchiveDaoParam toRepository() {
        return GoalArchiveDaoParam.builder()
                .accountId(accountId)
                .page(page)
                .fromCreateDate(fromDate)
                .toCreateDate(toDate)
                .build();
    }
}
