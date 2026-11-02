package daiku.app.controller.input.goalFavorite;

import daiku.app.service.input.goalFavorite.GoalFavoriteSearchServiceInput;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalFavoriteSearchParameter {
    int year;
    int page;

    public GoalFavoriteSearchServiceInput toService(Long accountId) {
        return GoalFavoriteSearchServiceInput.builder()
                .accountId(accountId)
                .fromDate(LocalDate.of(year, 1, 1))
                .toDate(LocalDate.of(year + 1, 1, 1))
                .page(page)
                .build();
    }

}
