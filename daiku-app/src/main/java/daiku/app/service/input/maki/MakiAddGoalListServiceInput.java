package daiku.app.service.input.maki;

import daiku.domain.model.param.MakiDaoParam;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiAddGoalListServiceInput {
    Long accountId;
    Long makiId;

    public MakiDaoParam toMakiDetailParam() {
        return MakiDaoParam.builder()
                .accountId(accountId)
                .makiId(makiId)
                .build();
    }
}
