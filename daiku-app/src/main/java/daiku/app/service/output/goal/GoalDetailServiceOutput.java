package daiku.app.service.output.goal;

import daiku.domain.model.res.GoalSearchModel;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GoalDetailServiceOutput {
    GoalSearchModel goal;

    public GoalSearchModel toResponse() {
        return goal;
    }
}
