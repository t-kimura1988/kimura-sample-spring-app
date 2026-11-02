package daiku.domain.model.param;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProcessHistoryDuringProcessParam {
    private LocalDate processHistoryDate;
    private Long accountId;
}
