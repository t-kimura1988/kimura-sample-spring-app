package daiku.app.service.input.storyCharacter;

import daiku.domain.entity.TStoryCharacters;
import daiku.domain.enums.LeaderFlg;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StoryCharacterListServiceInput {
    Long storyId;
    Long ideaId;
    String charaName;
    String charaDesc;
    LeaderFlg leaderFlg;

    public TStoryCharacters toEntity() {
        TStoryCharacters entity = new TStoryCharacters();
        entity.setStoryId(storyId);
        entity.setIdeaId(ideaId);
        entity.setCharaName(charaName);
        entity.setCharaDesc(charaDesc);
        entity.setLeaderFlg(leaderFlg);
        return entity;
    }
}
