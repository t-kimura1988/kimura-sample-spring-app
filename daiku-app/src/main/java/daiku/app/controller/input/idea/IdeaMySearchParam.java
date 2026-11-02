package daiku.app.controller.input.idea;

import daiku.app.service.input.idea.IdeaMySearchServiceInput;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IdeaMySearchParam {
    int page;

    public IdeaMySearchServiceInput toService(Long accountId) {
        return IdeaMySearchServiceInput.builder()
                .accountId(accountId)
                .page(page).build();
    }
}
