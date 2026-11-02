package daiku.app.service.output.story;

import daiku.domain.model.res.IdeaSearchModel;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StoryBodyUpdateServiceOutput {
    IdeaSearchModel ideaSearchModel;

    public IdeaSearchModel toRes() {
        return ideaSearchModel;
    }
}
