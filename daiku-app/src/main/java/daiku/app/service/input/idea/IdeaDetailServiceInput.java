package daiku.app.service.input.idea;

import daiku.domain.model.param.IdeaDaoParam;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IdeaDetailServiceInput {
    Long ideaId;
    Long accountId;

    public IdeaDaoParam toDaoParam() {
        return IdeaDaoParam.builder()
                .accountId(accountId)
                .ideaId(ideaId).build();
    }
}
