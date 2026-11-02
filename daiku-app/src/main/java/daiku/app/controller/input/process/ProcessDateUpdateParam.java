package daiku.app.controller.input.process;

import daiku.app.service.input.process.ProcessDateUpdateServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class ProcessDateUpdateParam {
    @NotNull
    Long processId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate goalCreateDate;
    @NotNull
    Long goalId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate processStartDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate processEndDate;

    public ProcessDateUpdateServiceInput toService(Long accountId) {
        return ProcessDateUpdateServiceInput.builder()
                .goalId(goalId)
                .processId(processId)
                .accountId(accountId)
                .processStartDate(processStartDate)
                .processEndDate(processEndDate).build();
    }
}
