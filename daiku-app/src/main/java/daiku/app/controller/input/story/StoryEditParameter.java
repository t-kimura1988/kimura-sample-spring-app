package daiku.app.controller.input.story;

import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.groups.UpdateGroups;
import daiku.app.service.input.story.StoryCreateServiceInput;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class StoryEditParameter {
    @NotNull(groups = UpdateGroups.class)
    Long ideaId;
    @NotNull(groups = {UpdateGroups.class, CreateGroups.class})
    String title;

    public StoryCreateServiceInput toService(Long accountId) {
        return StoryCreateServiceInput.builder()
                .ideaId(ideaId)
                .title(title)
                .accountId(accountId).build();
    }
}
