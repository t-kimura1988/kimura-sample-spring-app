package daiku.app.service.output.goal;

import daiku.domain.model.res.GoalArchiveSearchModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GoalArchiveEditDisplayServiceOutput {
    GoalArchiveSearchModel goalArchiveInfo;

    public GoalArchiveSearchModel toResponse() {
        return goalArchiveInfo;
    }
}
