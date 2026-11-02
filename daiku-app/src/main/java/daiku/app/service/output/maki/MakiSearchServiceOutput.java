package daiku.app.service.output.maki;

import daiku.domain.model.res.MakiSearchModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MakiSearchServiceOutput {
    List<MakiSearchModel> makiList;

    public List<MakiSearchModel> toResponse() {
        return makiList;
    }
}
