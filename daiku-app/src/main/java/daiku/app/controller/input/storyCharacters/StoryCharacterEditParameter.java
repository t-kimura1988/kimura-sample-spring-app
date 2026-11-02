package daiku.app.controller.input.storyCharacters;

import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.groups.UpdateGroups;
import daiku.app.service.input.storyCharacter.StoryCharacterCreateServiceInput;
import daiku.domain.enums.LeaderFlg;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@Builder
public class StoryCharacterEditParameter {
    @NotNull(groups = UpdateGroups.class)
    Long storyCharacterId;
    @NotNull(groups = CreateGroups.class)
    Long storyId;
    @NotNull(groups = CreateGroups.class)
    Long ideaId;
    @NotBlank(groups = {UpdateGroups.class, CreateGroups.class})
    @Size(max = 50, groups = {UpdateGroups.class, CreateGroups.class})
    String charaName;
    @NotBlank(groups = {UpdateGroups.class, CreateGroups.class})
    @Size(max = 100, groups = {UpdateGroups.class, CreateGroups.class})
    String charaDesc;
    @NotNull(groups = {UpdateGroups.class, CreateGroups.class})
    LeaderFlg leaderFlg;

    public StoryCharacterCreateServiceInput toService() {
        return StoryCharacterCreateServiceInput.builder()
                .storyId(storyId)
                .ideaId(ideaId)
                .charaName(charaName)
                .charaDesc(charaDesc)
                .leaderFlg(leaderFlg).build();
    }
}
