package daiku.app.service.input.maki;

import daiku.domain.model.param.MakiDaoParam;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiDetailServiceInput {
    Long accountId;
    Long makiId;

    public MakiDaoParam toRepository() {
        return MakiDaoParam.builder()
                .accountId(accountId)
                .makiId(makiId)
                .build();
    }
}
