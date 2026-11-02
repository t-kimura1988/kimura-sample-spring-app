package daiku.app.controller.input.processHistory;

import daiku.app.service.input.processHistory.ProcessHistoryDetailServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class ProcessHistoryDetailParam {
    Long processHistoryId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate goalCreateDate;

    public ProcessHistoryDetailServiceInput toService(Long accountId) {
        return ProcessHistoryDetailServiceInput.builder()
                .accountId(accountId)
                .processHistoryId(processHistoryId)
                .goalCreateDate(goalCreateDate)
                .build();
    }
}
