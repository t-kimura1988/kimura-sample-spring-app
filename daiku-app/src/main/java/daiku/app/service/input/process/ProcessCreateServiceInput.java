package daiku.app.service.input.process;

import daiku.domain.entity.TProcesses;
import daiku.domain.entity.TProcessesHistory;
import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProcessCreateServiceInput {
    Long goalId;
    Long accountId;
    String title;
    String body;
    ProcessStatus processStatus;
    ProcessPriority processPriority;

    public TProcesses toProcessEntity(LocalDate createDate) {
        TProcesses entity = new TProcesses();
        entity.setGoalId(goalId);
        entity.setAccountId(accountId);
        entity.setGoalCreateDate(createDate);
        entity.setTitle(title);
        entity.setBody(body);
        return entity;
    }

    public TProcessesHistory toProcessHistoryEntity(TProcesses processes) {
        TProcessesHistory entity = new TProcessesHistory();
        entity.setProcessId(processes.getId());
        entity.setAccountId(accountId);
        entity.setGoalCreateDate(processes.getGoalCreateDate());
        entity.setProcessStatus(processStatus);
        entity.setPriority(processPriority);
        entity.setProcessStartDate(LocalDate.now());
        entity.setProcessEndDate(LocalDate.now());
        return entity;

    }
}
