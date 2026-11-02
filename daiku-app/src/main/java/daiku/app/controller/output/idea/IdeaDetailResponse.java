package daiku.app.controller.output.idea;

import daiku.domain.model.res.IdeaSearchModel;
import daiku.domain.model.res.StoryCharacterSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class IdeaDetailResponse {
    IdeaSearchModel ideaSearchModel;
    List<StoryCharacterSearchModel> storyCharacterSearchModels;
}
