package daiku.app.service.input.idea;

import daiku.domain.model.param.IdeaDaoParam;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IdeaMySearchServiceInput {
    Long accountId;
    int page;

    public IdeaDaoParam toDaoParam() {
        return IdeaDaoParam.builder()
                .accountId(accountId)
                .page(page).build();
    }
}
