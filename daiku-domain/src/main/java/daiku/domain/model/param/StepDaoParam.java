package daiku.domain.model.param;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StepDaoParam {
    private Long stepId;
    private LocalDate createDate;
    private Long accountId;
}
