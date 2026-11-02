package daiku.app.controller.input.maki;

import daiku.app.service.input.maki.MakiAddGoalListServiceInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiAddGoalListParam {
    Long makiId;

    public MakiAddGoalListServiceInput toService(Long accountId) {
        return MakiAddGoalListServiceInput.builder()
                .accountId(accountId)
                .makiId(makiId).build();
    }
}
