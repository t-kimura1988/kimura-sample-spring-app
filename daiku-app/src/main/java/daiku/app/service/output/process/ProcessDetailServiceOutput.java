package daiku.app.service.output.process;

import daiku.domain.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessDetailServiceOutput {
    ProcessSearchModel process;

    public ProcessSearchModel toResponse() {
        return process;
    }
}
