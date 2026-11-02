package daiku.app.service.input.idea;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IdeaUpdateServiceInput {
    Long ideaId;
    Long accountId;
    String body;
}
