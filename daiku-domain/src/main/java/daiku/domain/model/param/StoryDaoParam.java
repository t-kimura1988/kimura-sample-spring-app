package daiku.domain.model.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoryDaoParam {
    private Long storyId;
    private Long ideaId;
    private Long accountId;
    private int page;
}
