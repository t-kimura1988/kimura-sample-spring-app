package daiku.app.service.input.maki;

import daiku.domain.model.param.MakiDaoParam;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiSearchServiceInput {
    Long accountId;
    int page;

    public MakiDaoParam toRepository() {
        return MakiDaoParam.builder()
                .accountId(accountId)
                .page(page)
                .build();
    }
}
