package daiku.app.controller.input.story;

import daiku.app.service.input.story.StoryDetailServiceInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StoryDetailParameter {
    Long ideaId;
    Long storyId;

    public StoryDetailServiceInput toService(Long accountId) {
        return StoryDetailServiceInput.builder()
                .ideaId(ideaId)
                .storyId(storyId)
                .accountId(accountId).build();
    }
}
