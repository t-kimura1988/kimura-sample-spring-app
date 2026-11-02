package daiku.app.service.output.idea;

import daiku.domain.model.res.IdeaSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class IdeaMySearchServiceOutput {
    List<IdeaSearchModel> ideaSearchModelList;

    public List<IdeaSearchModel> toRes() {
        return ideaSearchModelList;
    }
}
