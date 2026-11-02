package daiku.app.controller.input.processHistory;

import daiku.app.service.input.processHistory.ProcessHistoryUpdateStatusServiceInput;
import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class ProcessHistoryUpdateStatusParam {
    @NotNull
    Long processId;
    @NotNull
    ProcessStatus processStatus;
    @NotNull
    ProcessPriority priority;

    public ProcessHistoryUpdateStatusServiceInput toService(Long accountId) {
        return ProcessHistoryUpdateStatusServiceInput.builder()
                .processId(processId)
                .accountId(accountId)
                .processStatus(processStatus)
                .priority(priority).build();
    }
}
