package daiku.app.service.output.process;

import daiku.domain.model.res.ProcessSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProcessSearchServiceOutput {
    List<ProcessSearchModel> processSearchModelList;

    public List<ProcessSearchModel> toResponse() {
        return processSearchModelList;
    }
}
