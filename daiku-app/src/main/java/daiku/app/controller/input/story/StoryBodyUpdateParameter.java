package daiku.app.controller.input.story;

import daiku.app.service.input.story.StoryBodyUpdateServiceInput;
import lombok.Builder;
import lombok.Value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Value
@Builder
public class StoryBodyUpdateParameter {
    @NotNull
    Long storyId;
    @NotNull
    Long ideaId;
    @NotBlank
    String storyBody;

    public StoryBodyUpdateServiceInput toService(Long accountId) {
        return StoryBodyUpdateServiceInput.builder()
                .storyId(storyId)
                .ideaId(ideaId)
                .accountId(accountId)
                .body(storyBody).build();
    }
}
