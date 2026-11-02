package daiku.app.service.input.story;

import daiku.domain.entity.TStories;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StoryCreateServiceInput {
    Long accountId;
    Long ideaId;
    String title;

    public TStories toEntity() {
        TStories stories = new TStories();
        stories.setIdeaId(ideaId);
        stories.setAccountId(accountId);
        stories.setTitle(title);
        return stories;
    }
}
