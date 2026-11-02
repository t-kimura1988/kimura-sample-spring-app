package daiku.app.service.input.goalFavorite;

import daiku.domain.model.param.GoalFavoriteDaoParam;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalFavoriteSearchServiceInput {
    Long accountId;
    LocalDate fromDate;
    LocalDate toDate;
    int page;

    public GoalFavoriteDaoParam toParam() {
        return GoalFavoriteDaoParam.builder()
                .favoriteCreateDateFrom(fromDate)
                .favoriteCreateDateTo(toDate)
                .accountId(accountId)
                .page(page).build();
    }
}
