package daiku.app.service.output.maki;

import daiku.domain.model.res.MakiSearchModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MakiDetailServiceOutput {
    MakiSearchModel maki;

    public MakiSearchModel toResponse() {
        return maki;
    }
}
