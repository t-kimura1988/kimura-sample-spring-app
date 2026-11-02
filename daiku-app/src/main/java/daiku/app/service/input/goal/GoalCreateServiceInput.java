package daiku.app.service.input.goal;

import daiku.domain.entity.TGoals;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalCreateServiceInput {
    String title;
    String purpose;
    String aim;
    Long accountId;
    LocalDate dueDate;
    Long makiId;

    public TGoals toEntity() {
        var goals = new TGoals();
        goals.setTitle(title);
        goals.setCreateDate(LocalDate.now());
        goals.setPurpose(purpose);
        goals.setAim(aim);
        goals.setAccountId(accountId);
        goals.setDueDate(dueDate);
        return goals;
    }
}
