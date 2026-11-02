package daiku.app.controller.input.maki;

import daiku.app.service.input.maki.MakiGoalListServiceInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiGoalListParam {
    int page;
    Long makiId;

    public MakiGoalListServiceInput toService(Long accountId) {
        return MakiGoalListServiceInput.builder()
                .page(page)
                .accountId(accountId)
                .makiId(makiId).build();
    }
}
