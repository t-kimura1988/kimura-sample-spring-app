package daiku.domain.model.param;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProcessHistoryDaoParam {
    private Long id;
    private Long processId;
    private Long accountId;
    private LocalDate goalCreateDate;
    private Boolean latestFlg;
}
