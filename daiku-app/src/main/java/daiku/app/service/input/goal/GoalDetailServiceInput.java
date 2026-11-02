package daiku.app.service.input.goal;

import daiku.domain.model.param.GoalDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalDetailServiceInput {
    Long goalId;
    Long accountId;
    LocalDate createDate;

    public GoalDaoParam toParam() {
        return GoalDaoParam.builder()
                .goalId(goalId)
                .accountId(accountId)
                .createDate(createDate).build();
    }
}
