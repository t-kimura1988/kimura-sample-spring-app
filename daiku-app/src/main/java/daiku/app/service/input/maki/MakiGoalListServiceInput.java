package daiku.app.service.input.maki;

import daiku.domain.model.param.MakiDaoParam;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiGoalListServiceInput {
    Long accountId;
    Long makiId;
    int page;

    public MakiDaoParam toMakiDetailParam() {
        return MakiDaoParam.builder()
                .accountId(accountId)
                .makiId(makiId)
                .page(page)
                .build();
    }
}
