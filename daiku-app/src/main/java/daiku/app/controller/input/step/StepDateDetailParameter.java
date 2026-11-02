package daiku.app.controller.input.step;

import daiku.app.service.input.step.StepDateDetailServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class StepDateDetailParameter {
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate createDate;

    public StepDateDetailServiceInput toService(Long accountId) {
        return StepDateDetailServiceInput.builder()
                .accountId(accountId)
                .createDate(createDate)
                .build();
    }
}
