package daiku.app.controller.input.maki;

import daiku.app.service.input.maki.MakiDetailServiceInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiDetailParam {
    Long makiId;

    public MakiDetailServiceInput toService(Long accountId) {
        return MakiDetailServiceInput.builder()
                .makiId(makiId)
                .accountId(accountId).build();
    }
}
