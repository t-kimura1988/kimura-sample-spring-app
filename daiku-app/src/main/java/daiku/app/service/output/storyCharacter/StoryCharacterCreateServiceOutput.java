package daiku.app.service.output.storyCharacter;

import daiku.domain.model.res.StoryCharacterSearchModel;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StoryCharacterCreateServiceOutput {
    StoryCharacterSearchModel storyCharacterSearchModel;

    public StoryCharacterSearchModel toRes() {
        return storyCharacterSearchModel;
    }
}
