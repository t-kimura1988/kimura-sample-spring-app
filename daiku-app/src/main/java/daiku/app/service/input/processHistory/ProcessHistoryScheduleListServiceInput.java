package daiku.app.service.input.processHistory;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class ProcessHistoryScheduleListServiceInput {
    Long accountId;
    LocalDate processHistoryDate;

}
