package daiku.app.service.input.process;

import daiku.domain.model.param.ProcessDaoParam;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessDetailServiceInput {
    Long processId;
    Long accountId;

    public ProcessDaoParam toParameter() {
        return ProcessDaoParam.builder()
                .id(processId)
                .accountId(accountId).build();
    }
}
