package daiku.app.service.output.story;

import daiku.domain.model.res.StorySearchModel;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StoryDetailServiceOutput {
    StorySearchModel storySearchModel;

    public StorySearchModel toRes() {
        return storySearchModel;
    }
}
