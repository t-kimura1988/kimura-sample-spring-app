package daiku.app.controller.input.goalFavorite;

import daiku.app.service.input.goalFavorite.FavoriteCreateServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class GoalFavoriteCreateParam {
    @NotNull
    Long goalId;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate goalCreateDate;

    public FavoriteCreateServiceInput toService(Long accountId) {
        return FavoriteCreateServiceInput.builder()
                .goalId(goalId)
                .accountId(accountId)
                .createDate(goalCreateDate).build();
    }

}
