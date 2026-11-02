package daiku.app.service.input.story;

import daiku.domain.model.param.StoryDaoParam;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StoryDetailServiceInput {
    Long accountId;
    Long ideaId;
    Long storyId;

    public StoryDaoParam toDaoParam() {
        return StoryDaoParam.builder()
                .storyId(storyId)
                .ideaId(ideaId)
                .accountId(accountId)
                .build();
    }
}
