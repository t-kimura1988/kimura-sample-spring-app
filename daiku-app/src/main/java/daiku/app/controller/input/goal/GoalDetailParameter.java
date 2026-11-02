package daiku.app.controller.input.goal;

import daiku.app.service.input.goal.GoalDetailServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class GoalDetailParameter {
    @NotNull
    Long goalId;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate createDate;

    public GoalDetailServiceInput toService(Long accountId) {
        return GoalDetailServiceInput.builder()
                .goalId(goalId)
                .accountId(accountId)
                .createDate(createDate).build();
    }
}
