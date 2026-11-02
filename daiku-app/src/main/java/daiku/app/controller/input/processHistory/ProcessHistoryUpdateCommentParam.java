package daiku.app.controller.input.processHistory;

import daiku.app.service.input.processHistory.ProcessHistoryUpdateCommentServiceInput;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class ProcessHistoryUpdateCommentParam {
    @NotNull
    Long processHistoryId;
    @NotNull
    @NotEmpty
    String comment;

    public ProcessHistoryUpdateCommentServiceInput toService(Long accountId) {
        return ProcessHistoryUpdateCommentServiceInput.builder()
                .accountId(accountId)
                .processHistory(processHistoryId)
                .comment(comment).build();
    }
}
