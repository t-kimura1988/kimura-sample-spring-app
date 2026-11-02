package daiku.app.controller.input.maki;

import daiku.domain.entity.TMakiGoalRelation;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class MakiAddGoalParam {
    Long goalId;
    Long makiId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate goalCreateDate;

    public TMakiGoalRelation toEntity(Long maxNum) {
        TMakiGoalRelation entity = new TMakiGoalRelation();
        entity.setGoalId(goalId);
        entity.setGoalCreateDate(goalCreateDate);
        entity.setMakiId(makiId);
        entity.setSortNum(maxNum);
        return entity;
    }
}
