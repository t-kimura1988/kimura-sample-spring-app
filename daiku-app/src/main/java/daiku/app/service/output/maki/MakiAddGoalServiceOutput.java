package daiku.app.service.output.maki;

import daiku.domain.entity.TMakiGoalRelation;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MakiAddGoalServiceOutput {
    List<TMakiGoalRelation> list;

    public List<TMakiGoalRelation> toResponse() {
        return list;
    }
}
