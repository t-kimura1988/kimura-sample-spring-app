package daiku.app.service.output.goal;

import daiku.domain.model.res.GoalArchiveSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GoalArchiveSearchServiceOutput {
    List<GoalArchiveSearchModel> goalList;

    public List<GoalArchiveSearchModel> toResponse() {
        return goalList;
    }
}
