package daiku.app.service.input.goal;

import daiku.domain.model.param.GoalDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalSearchServiceInput {
    Long accountId;
    LocalDate fromDate;
    LocalDate toDate;
    int page;

    public GoalDaoParam toRepository() {
        return GoalDaoParam.builder()
                .accountId(accountId)
                .olderThan(fromDate.getYear() < LocalDate.now().getYear() - 10)
                .fromCreateDate(fromDate)
                .toCreateDate(toDate)
                .page(page)
                .build();
    }
}
