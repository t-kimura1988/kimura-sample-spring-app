package daiku.app.service.input.step;

import daiku.domain.entity.TSteps;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class StepCreateServiceInput {
    Long accountId;
    String body;
    LocalDate createDate;

    public TSteps toEntity() {
        TSteps steps = new TSteps();
        steps.setCreateDate(createDate);
        steps.setAccountId(accountId);
        steps.setBody(body);
        return steps;
    }
}
