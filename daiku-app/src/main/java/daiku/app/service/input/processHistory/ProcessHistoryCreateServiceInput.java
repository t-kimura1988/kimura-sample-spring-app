package daiku.app.service.input.processHistory;

import daiku.domain.entity.TProcessesHistory;
import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import daiku.domain.model.param.ProcessDaoParam;
import daiku.domain.model.res.ProcessHistorySearchModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessHistoryCreateServiceInput {
    Long processId;
    Long accountId;
    String comment;
    ProcessStatus processStatus;
    ProcessPriority priority;

    public TProcessesHistory toProcessDetailParam(ProcessHistorySearchModel oldData) {
        TProcessesHistory entity = new TProcessesHistory();
        entity.setBeforeProcessStartDate(oldData.getProcessStartDate());
        entity.setBeforeProcessEndDate(oldData.getProcessEndDate());
        entity.setBeforePriority(oldData.getPriority());
        entity.setBeforeProcessStatus(oldData.getProcessStatus());
        entity.setGoalCreateDate(oldData.getGoalCreateDate());

        entity.setProcessStartDate(oldData.getProcessStartDate());
        entity.setProcessEndDate(oldData.getProcessEndDate());
        entity.setProcessStatus(processStatus);
        entity.setPriority(priority);
        entity.setComment(comment);
        entity.setProcessId(processId);
        entity.setAccountId(accountId);
        return entity;
    }

    public ProcessDaoParam toDetailParam() {
        return ProcessDaoParam.builder()
                .id(processId)
                .accountId(accountId).build();
    }
}
