package daiku.app.service.output.maki;

import daiku.domain.model.res.MakiAddGoalListModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MakiGoalListServiceOutput {
    List<MakiAddGoalListModel> makiGoalList;

    public List<MakiAddGoalListModel> toResponse() {
        return makiGoalList;
    }
}
