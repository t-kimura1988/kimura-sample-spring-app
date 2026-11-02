package daiku.app.controller.input.process;

import daiku.app.service.input.process.ProcessDetailServiceInput;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class ProcessDetailParam {
    Long processId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate goalCreateDate;

    public ProcessDetailServiceInput toService(Long accountId) {
        return ProcessDetailServiceInput.builder()
                .processId(processId)
                .accountId(accountId).build();
    }
}
