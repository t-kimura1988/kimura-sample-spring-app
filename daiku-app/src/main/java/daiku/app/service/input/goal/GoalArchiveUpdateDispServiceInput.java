package daiku.app.service.input.goal;

import daiku.domain.model.param.GoalArchiveDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveUpdateDispServiceInput {
    Long archiveId;
    LocalDate archiveCreateDate;

    public GoalArchiveDaoParam toArchiveRepository() {
        return GoalArchiveDaoParam.builder()
                .archiveId(archiveId)
                .archiveCreateDate(archiveCreateDate)
                .build();
    }
}
