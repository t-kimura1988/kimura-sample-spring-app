package daiku.app.service.output.step;

import daiku.domain.model.res.StepSearchModel;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StepDateDetailServiceOutput {
    StepSearchModel stepSearchModel;

    public StepSearchModel toRes() {
        return stepSearchModel;
    }
}
