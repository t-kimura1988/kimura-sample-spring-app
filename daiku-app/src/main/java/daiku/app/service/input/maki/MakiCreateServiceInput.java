package daiku.app.service.input.maki;

import daiku.domain.entity.TMakis;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiCreateServiceInput {
    String makiTitle;
    String makiKey;
    String makiDesc;
    Long accountId;

    public TMakis toEntity() {
        var makis = new TMakis();
        makis.setMakiTitle(makiTitle);
        makis.setMakiKey(makiKey);
        makis.setMakiDesc(makiDesc);
        makis.setAccountId(accountId);
        return makis;
    }
}
