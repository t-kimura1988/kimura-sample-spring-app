package daiku.app.controller.input.goal;

import daiku.app.service.input.goal.GoalArchiveEditDisplayServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveEditDispParameter {
    Long archiveId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate archiveCreateDate;

    public GoalArchiveEditDisplayServiceInput toService(Long accountId) {
        return GoalArchiveEditDisplayServiceInput.builder()
                .archiveId(archiveId)
                .accountId(accountId)
                .archiveCreateDate(archiveCreateDate)
                .build();
    }

}
