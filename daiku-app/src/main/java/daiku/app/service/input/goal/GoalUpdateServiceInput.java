package daiku.app.service.input.goal;

import daiku.domain.entity.TGoals;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalUpdateServiceInput {
    Long goalId;
    LocalDate createDate;
    String title;
    String purpose;
    String aim;
    Long accountId;
    LocalDate dueDate;

    public TGoals toEntity() {
        var goals = new TGoals();
        goals.setId(goalId);
        goals.setCreateDate(createDate);
        goals.setTitle(title);
        goals.setPurpose(purpose);
        goals.setAim(aim);
        goals.setAccountId(accountId);
        goals.setDueDate(dueDate);
        return goals;
    }
}
