package daiku.app.controller.input.processHistory;

import daiku.app.service.input.processHistory.ProcessHistoryScheduleListServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class ProcessHistoryScheduleListParam {
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate processHistoryDate;

    public ProcessHistoryScheduleListServiceInput toService(Long accountId) {
        return ProcessHistoryScheduleListServiceInput.builder()
                .processHistoryDate(processHistoryDate)
                .accountId(accountId).build();
    }
}
