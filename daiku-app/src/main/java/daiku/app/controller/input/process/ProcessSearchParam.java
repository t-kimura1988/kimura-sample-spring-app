package daiku.app.controller.input.process;

import daiku.app.service.input.process.ProcessSearchServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class ProcessSearchParam {
    Long goalId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate createDate;

    public ProcessSearchServiceInput toService(Long accountId) {
        return ProcessSearchServiceInput.builder()
                .accountId(accountId)
                .createDate(createDate)
                .goalId(goalId).build();
    }
}
