package daiku.app.service.input.step;

import daiku.domain.model.param.StepDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class StepDateDetailServiceInput {
    Long accountId;
    LocalDate createDate;

    public StepDaoParam toParam() {
        return StepDaoParam.builder()
                .createDate(createDate)
                .accountId(accountId)
                .build();
    }
}
