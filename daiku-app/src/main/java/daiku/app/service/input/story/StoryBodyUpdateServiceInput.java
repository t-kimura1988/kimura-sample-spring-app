package daiku.app.service.input.story;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StoryBodyUpdateServiceInput {
    Long accountId;
    Long storyId;
    Long ideaId;
    String body;
}
