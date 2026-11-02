package daiku.app.service.input.processHistory;

import daiku.domain.entity.TProcessesHistory;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessHistoryUpdateCommentServiceInput {
    Long processHistory;
    Long accountId;
    String comment;

    public TProcessesHistory toProcessHistoryEntity() {
        TProcessesHistory entity = new TProcessesHistory();
        entity.setId(processHistory);
        entity.setComment(comment);
        return entity;
    }
}
