package daiku.domain.model.res;

import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;

@Data
@Entity
public class MakiGoalSearchModel {
    private Long id;
    private Long goalId;
    private Long makiId;
    private Long accountId;
    private LocalDate goalCreateDate;
}
