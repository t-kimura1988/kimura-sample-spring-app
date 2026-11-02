package daiku.app.service.input.processHistory;

import daiku.domain.entity.TProcessesHistory;
import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import daiku.domain.model.param.ProcessDaoParam;
import daiku.domain.model.res.ProcessHistorySearchModel;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ProcessHistoryUpdateStatusServiceInput {
    Long processId;
    Long accountId;
    ProcessStatus processStatus;
    ProcessPriority priority;

    public ProcessDaoParam toDetailParam() {
        return ProcessDaoParam.builder()
                .id(processId)
                .accountId(accountId).build();
    }

    public TProcessesHistory toProcessDetailParam(ProcessHistorySearchModel oldData) {
        TProcessesHistory entity = new TProcessesHistory();
        entity.setBeforeProcessStartDate(oldData.getProcessStartDate());
        entity.setBeforeProcessEndDate(oldData.getProcessEndDate());
        entity.setBeforePriority(oldData.getPriority());
        entity.setBeforeProcessStatus(oldData.getProcessStatus());
        entity.setGoalCreateDate(oldData.getGoalCreateDate());
        entity.setBeforeProcessStartDate(oldData.getBeforeProcessStartDate());
        entity.setProcessStartDate(oldData.getProcessStartDate());
        entity.setBeforeProcessEndDate(oldData.getBeforeProcessEndDate());
        entity.setProcessEndDate(oldData.getProcessEndDate());
        entity.setProcessStatus(processStatus);
        entity.setPriority(priority);
        entity.setProcessId(processId);
        entity.setAccountId(accountId);
        return entity;
    }

}
