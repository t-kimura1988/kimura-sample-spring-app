package daiku.app.controller.input.storyCharacters;

import daiku.app.service.input.storyCharacter.StoryCharacterListServiceInput;
import lombok.Builder;
import lombok.Value;

import jakarta.validation.constraints.NotNull;

@Value
@Builder
public class StoryCharacterListParameter {
    @NotNull
    Long storyId;
    @NotNull
    Long ideaId;

    public StoryCharacterListServiceInput toService() {
        return StoryCharacterListServiceInput.builder()
                .storyId(storyId)
                .ideaId(ideaId).build();
    }
}
