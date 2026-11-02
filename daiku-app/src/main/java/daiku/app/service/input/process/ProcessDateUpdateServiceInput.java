package daiku.app.service.input.process;

import daiku.domain.entity.TProcessesHistory;
import daiku.domain.model.res.ProcessHistorySearchModel;
import daiku.domain.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProcessDateUpdateServiceInput {
    Long goalId;
    Long processId;
    Long accountId;
    LocalDate goalCreateDate;
    LocalDate processStartDate;
    LocalDate processEndDate;

    public TProcessesHistory toProcessUpdateHistory(ProcessHistorySearchModel oldData, ProcessSearchModel oldProcess) {
        TProcessesHistory entity = new TProcessesHistory();
        entity.setBeforeProcessStartDate(oldData.getProcessStartDate());
        entity.setBeforeProcessEndDate(oldData.getProcessEndDate());
        entity.setBeforePriority(oldData.getPriority());
        entity.setBeforeProcessStatus(oldData.getProcessStatus());
        entity.setGoalCreateDate(oldData.getGoalCreateDate());
        entity.setBeforeProcessStartDate(oldData.getProcessStartDate());
        entity.setBeforeProcessEndDate(oldData.getProcessEndDate());
        entity.setProcessStartDate(processStartDate);
        entity.setProcessEndDate(processEndDate);
        entity.setProcessStatus(oldData.getProcessStatus());
        entity.setPriority(oldData.getPriority());
        entity.setProcessId(processId);
        entity.setAccountId(accountId);
        entity.setBeforeTitle(oldProcess.getBeforeTitle());
        entity.setBeforeBody(oldProcess.getBeforeBody());
        return entity;
    }
}
