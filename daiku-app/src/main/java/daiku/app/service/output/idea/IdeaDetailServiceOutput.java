package daiku.app.service.output.idea;

import daiku.domain.model.res.IdeaSearchModel;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IdeaDetailServiceOutput {
    IdeaSearchModel idea;

    public IdeaSearchModel toRes() {
        return idea;
    }
}
