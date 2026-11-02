package daiku.app.controller.input.maki;

import daiku.app.service.input.maki.MakiSearchServiceInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiSearchParam {
    int page;

    public MakiSearchServiceInput toService(Long accountId) {
        return MakiSearchServiceInput.builder()
                .page(page)
                .accountId(accountId).build();
    }
}
