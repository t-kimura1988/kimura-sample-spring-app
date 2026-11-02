package daiku.app.service.input.maki;

import daiku.app.controller.input.maki.MakiAddGoalParam;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MakiAddGoalServiceInput {
    Long accountId;
    List<MakiAddGoalParam> body;
}
