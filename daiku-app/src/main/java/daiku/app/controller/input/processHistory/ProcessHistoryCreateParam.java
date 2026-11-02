package daiku.app.controller.input.processHistory;

import daiku.app.service.input.processHistory.ProcessHistoryCreateServiceInput;
import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class ProcessHistoryCreateParam {
    @NotNull
    Long processId;
    String comment;
    ProcessStatus processStatus;
    ProcessPriority priority;

    public ProcessHistoryCreateServiceInput toService(Long accountId) {
        return ProcessHistoryCreateServiceInput.builder()
                .accountId(accountId)
                .processId(processId)
                .processStatus(processStatus)
                .priority(priority)
                .comment(comment).build();
    }
}
