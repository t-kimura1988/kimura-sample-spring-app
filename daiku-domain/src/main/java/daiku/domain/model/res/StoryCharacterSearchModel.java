package daiku.domain.model.res;

import daiku.domain.enums.LeaderFlg;
import lombok.Data;
import org.seasar.doma.Entity;

@Data
@Entity
public class StoryCharacterSearchModel {
    private Long id;
    private Long ideaId;
    private Long storyId;
    private String charaName;
    private String charaDesc;
    private LeaderFlg leaderFlg;
}
