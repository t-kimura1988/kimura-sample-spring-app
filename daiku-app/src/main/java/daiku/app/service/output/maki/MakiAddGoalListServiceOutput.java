package daiku.app.service.output.maki;

import daiku.domain.model.res.GoalAddListItemModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MakiAddGoalListServiceOutput {
    List<GoalAddListItemModel> list;

    public List<GoalAddListItemModel> toResponse() {
        return list;
    }
}
