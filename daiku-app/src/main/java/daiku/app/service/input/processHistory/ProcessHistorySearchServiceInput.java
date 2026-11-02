package daiku.app.service.input.processHistory;

import daiku.domain.model.param.ProcessDaoParam;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessHistorySearchServiceInput {
    Long processId;
    Long accountId;

    public ProcessDaoParam toProcessDetailParam() {
        return ProcessDaoParam.builder()
                .accountId(accountId)
                .id(processId).build();
    }
}
