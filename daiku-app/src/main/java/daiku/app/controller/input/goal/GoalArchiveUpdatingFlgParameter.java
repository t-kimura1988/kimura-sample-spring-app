package daiku.app.controller.input.goal;

import daiku.app.service.input.goal.GoalArchiveUpdatingFlgServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveUpdatingFlgParameter {
    Long goalId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate goalCreateDate;

    public GoalArchiveUpdatingFlgServiceInput toService() {
        return GoalArchiveUpdatingFlgServiceInput.builder()
                .goalId(goalId)
                .goalCreateDate(goalCreateDate)
                .build();
    }

}
