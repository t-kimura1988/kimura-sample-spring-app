package daiku.app.service.output.goal;

import daiku.app.controller.output.GoalArchiveDetailResponse;
import daiku.domain.model.res.GoalArchiveSearchModel;
import daiku.domain.model.res.GoalSearchModel;
import daiku.domain.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GoalArchiveDetailServiceOutput {
    GoalArchiveSearchModel goalArchiveInfo;
    GoalSearchModel goalInfo;
    List<ProcessSearchModel> processInfo;

    public GoalArchiveDetailResponse toResponse() {
        return GoalArchiveDetailResponse.builder()
                .goalArchiveInfo(goalArchiveInfo)
                .goalInfo(goalInfo)
                .processInfo(processInfo).build();
    }
}
