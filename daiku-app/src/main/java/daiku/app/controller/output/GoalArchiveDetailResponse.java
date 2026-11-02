package daiku.app.controller.output;

import daiku.domain.model.res.GoalArchiveSearchModel;
import daiku.domain.model.res.GoalSearchModel;
import daiku.domain.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GoalArchiveDetailResponse {
    GoalArchiveSearchModel goalArchiveInfo;
    GoalSearchModel goalInfo;
    List<ProcessSearchModel> processInfo;
}
