package daiku.app.service.output.goalFavorite;

import daiku.domain.model.res.GoalFavoriteSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class GoalFavoriteSearchServiceOutput {
    List<GoalFavoriteSearchModel> goalFavoriteSearchModelList;

    public List<GoalFavoriteSearchModel> toResponse() {
        return goalFavoriteSearchModelList;
    }
}
