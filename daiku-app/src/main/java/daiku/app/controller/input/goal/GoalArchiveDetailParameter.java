package daiku.app.controller.input.goal;

import daiku.app.service.input.goal.GoalArchiveDetailServiceInput;
import daiku.app.service.input.goal.GoalArchiveUpdateDispServiceInput;
import daiku.app.service.input.goal.MyGoalArchiveDetailServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveDetailParameter {
    Long archiveId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate archiveCreateDate;

    public GoalArchiveDetailServiceInput toService(Long accountId) {
        return GoalArchiveDetailServiceInput.builder()
                .archiveId(archiveId)
                .accountId(accountId)
                .archiveCreateDate(archiveCreateDate)
                .build();
    }

    public MyGoalArchiveDetailServiceInput toMyArchiveService(Long accountId) {
        return MyGoalArchiveDetailServiceInput.builder()
                .archiveId(archiveId)
                .accountId(accountId)
                .archiveCreateDate(archiveCreateDate)
                .build();
    }

    public GoalArchiveUpdateDispServiceInput toArchiveUpdateDipsService() {
        return GoalArchiveUpdateDispServiceInput.builder()
                .archiveId(archiveId)
                .archiveCreateDate(archiveCreateDate)
                .build();
    }

}
