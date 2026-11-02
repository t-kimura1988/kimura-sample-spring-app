package daiku.app.controller.input.goal;

import com.fasterxml.jackson.annotation.JsonFormat;
import daiku.app.annotation.FutureFromNow;
import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.groups.UpdateGroups;
import daiku.app.service.input.goal.GoalCreateServiceInput;
import daiku.app.service.input.goal.GoalUpdateServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class GoalCreateParam {
    @NotNull(groups = UpdateGroups.class)
    Long goalId;
    @NotNull(groups = UpdateGroups.class)
    LocalDate createDate;
    Long makiId;
    @NotEmpty(groups = {UpdateGroups.class, CreateGroups.class})
    String title;
    String purpose;
    String aim;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureFromNow(groups = {UpdateGroups.class, CreateGroups.class})
    LocalDate dueDate;

    public GoalCreateServiceInput toCreateService(Long accountId) {
        return GoalCreateServiceInput.builder()
                .title(title)
                .purpose(purpose)
                .aim(aim)
                .dueDate(dueDate)
                .makiId(makiId)
                .accountId(accountId).build();
    }

    public GoalUpdateServiceInput toUpdateService(Long accountId) {
        return GoalUpdateServiceInput.builder()
                .goalId(goalId)
                .createDate(createDate)
                .title(title)
                .purpose(purpose)
                .aim(aim)
                .dueDate(dueDate)
                .accountId(accountId).build();
    }
}
