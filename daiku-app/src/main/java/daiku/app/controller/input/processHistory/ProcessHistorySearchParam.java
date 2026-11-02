package daiku.app.controller.input.processHistory;

import daiku.app.service.input.processHistory.ProcessHistorySearchServiceInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessHistorySearchParam {
    Long processId;

    public ProcessHistorySearchServiceInput toService(Long accountId) {
        return ProcessHistorySearchServiceInput.builder()
                .accountId(accountId)
                .processId(processId).build();
    }
}
