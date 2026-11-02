package daiku.app.controller.input.goal;

import daiku.app.service.input.goal.GoalSearchServiceInput;
import lombok.Builder;
import lombok.Value;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.YearMonth;

@Value
@Builder
public class GoalSearchParameter {
    int year;
    @Nullable
    int month;
    int page;

    public GoalSearchServiceInput toService(Long accountId) {
        LocalDate from;
        LocalDate to;
        if(month == 0) {
            from = LocalDate.of(year, 1, 1);
            to = LocalDate.of(year, 12, 31);
        } else {
            YearMonth yyyyMM = YearMonth.of(year, month);
            from = LocalDate.of(year, month, 1);
            to = LocalDate.of(year, month, yyyyMM.lengthOfMonth());
        }
        return GoalSearchServiceInput.builder()
                .accountId(accountId)
                .fromDate(from)
                .toDate(to)
                .page(page)
                .build();
    }

}
