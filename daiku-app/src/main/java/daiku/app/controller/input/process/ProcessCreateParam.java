package daiku.app.controller.input.process;

import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.groups.UpdateGroups;
import daiku.app.service.input.process.ProcessCreateServiceInput;
import daiku.app.service.input.process.ProcessUpdateServiceInput;
import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class ProcessCreateParam {
    @NotNull(groups = UpdateGroups.class)
    Long processId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(groups = {UpdateGroups.class, CreateGroups.class})
    LocalDate goalCreateDate;
    @NotNull(groups = {UpdateGroups.class, CreateGroups.class})
    Long goalId;
    @NotNull(groups = {UpdateGroups.class, CreateGroups.class})
    @NotEmpty(groups = {UpdateGroups.class, CreateGroups.class})
    String title;
    String body;
    @NotNull(groups = {UpdateGroups.class, CreateGroups.class})
    ProcessStatus processStatus;
    @NotNull(groups = {UpdateGroups.class, CreateGroups.class})
    ProcessPriority priority;

    public ProcessCreateServiceInput toService(Long accountId) {
        return ProcessCreateServiceInput.builder()
                .goalId(goalId)
                .accountId(accountId)
                .title(title)
                .body(body)
                .processStatus(processStatus)
                .processPriority(priority).build();
    }

    public ProcessUpdateServiceInput toUpdateService(Long accountId) {
        return ProcessUpdateServiceInput.builder()
                .processId(processId)
                .goalId(goalId)
                .goalCreateDate(goalCreateDate)
                .title(title)
                .body(body)
                .processStatus(processStatus)
                .processPriority(priority)
                .accountId(accountId).build();
    }
}
