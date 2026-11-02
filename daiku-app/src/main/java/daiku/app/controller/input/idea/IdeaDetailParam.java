package daiku.app.controller.input.idea;

import daiku.app.service.input.idea.IdeaDetailServiceInput;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Builder
@Value
public class IdeaDetailParam {
    @NotNull
    Long ideaId;

    public IdeaDetailServiceInput toService(Long accountId) {
        return IdeaDetailServiceInput.builder()
                .ideaId(ideaId)
                .accountId(accountId).build();
    }
}
