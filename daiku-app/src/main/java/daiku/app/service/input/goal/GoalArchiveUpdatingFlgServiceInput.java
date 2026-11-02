package daiku.app.service.input.goal;

import daiku.domain.entity.TGoalArchive;
import daiku.domain.enums.UpdatingFlg;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class GoalArchiveUpdatingFlgServiceInput {
    Long goalId;
    LocalDate goalCreateDate;
    Long accountId;

    public TGoalArchive toEntity(TGoalArchive oldData) {
        TGoalArchive entity = new TGoalArchive();
        entity.setId(oldData.getId());
        entity.setUpdatingFlg(UpdatingFlg.ARCHIVE_UPDATING);
        return entity;
    }
}
