package daiku.app.service.output.storyCharacter;

import daiku.domain.model.res.StoryCharacterSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class StoryCharacterListServiceOutput {
    List<StoryCharacterSearchModel> storyCharacterSearchModel;

    public List<StoryCharacterSearchModel> toRes() {
        return storyCharacterSearchModel;
    }
}
