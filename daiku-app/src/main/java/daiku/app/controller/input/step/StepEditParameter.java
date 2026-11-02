package daiku.app.controller.input.step;

import daiku.app.annotation.StepCreateDateVali;
import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.groups.UpdateGroups;
import daiku.app.service.input.step.StepCreateServiceInput;
import daiku.app.service.input.step.StepUpdateServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class StepEditParameter {
    @NotNull(groups = UpdateGroups.class)
    Long stepId;
    @NotBlank(groups = {UpdateGroups.class, CreateGroups.class})
    String body;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @StepCreateDateVali(groups = {UpdateGroups.class})
    LocalDate createDate;

    public StepCreateServiceInput toService(Long accountId) {

        return StepCreateServiceInput.builder()
                .accountId(accountId)
                .createDate(createDate)
                .body(body).build();
    }

    public StepUpdateServiceInput toUpdateService(Long accountId) {
        return StepUpdateServiceInput.builder()
                .stepId(stepId)
                .accountId(accountId)
                .body(body)
                .createDate(createDate).build();
    }
}
