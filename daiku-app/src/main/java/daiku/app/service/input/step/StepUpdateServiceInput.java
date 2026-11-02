package daiku.app.service.input.step;

import daiku.domain.entity.TSteps;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class StepUpdateServiceInput {
    Long accountId;
    Long stepId;
    String body;
    LocalDate createDate;

    public TSteps toEntity() {
        TSteps steps = new TSteps();
        steps.setId(stepId);
        steps.setCreateDate(createDate);
        steps.setAccountId(accountId);
        steps.setBody(body);
        return steps;
    }
}
