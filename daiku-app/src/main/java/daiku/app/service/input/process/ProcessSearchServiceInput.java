package daiku.app.service.input.process;

import daiku.domain.model.param.GoalDaoParam;
import daiku.domain.model.param.ProcessDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProcessSearchServiceInput {
    Long goalId;
    Long accountId;
    LocalDate createDate;

    public GoalDaoParam toGoalDaoParam() {
        return GoalDaoParam.builder()
                .accountId(accountId)
                .goalId(goalId)
                .createDate(createDate).build();
    }

    public ProcessDaoParam toProcessDaoParam() {
        return ProcessDaoParam.builder()
                .accountId(accountId)
                .goalId(goalId)
                .createDate(createDate).build();
    }
}
