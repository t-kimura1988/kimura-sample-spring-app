package daiku.domain.model.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdeaDaoParam {
    private Long ideaId;
    private Long accountId;
    private String body;
    private int page;
}
