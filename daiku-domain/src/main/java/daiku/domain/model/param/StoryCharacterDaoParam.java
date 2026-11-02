package daiku.domain.model.param;

import daiku.domain.enums.LeaderFlg;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoryCharacterDaoParam {
    private Long storyCharacterId;
    private Long storyId;
    private Long ideaId;
    private Long accountId;
    private LeaderFlg leaderFlg;
    private int page;
}
