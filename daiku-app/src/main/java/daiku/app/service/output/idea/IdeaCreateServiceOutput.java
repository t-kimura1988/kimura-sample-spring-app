package daiku.app.service.output.idea;

import daiku.domain.model.res.IdeaSearchModel;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IdeaCreateServiceOutput {
    IdeaSearchModel ideaSearchModel;

    public IdeaSearchModel toRes() {
        return ideaSearchModel;
    }
}
