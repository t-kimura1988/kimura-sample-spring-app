package daiku.domain.model.param.firestore;

import lombok.Builder;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
@Builder
public class BusinessDataAsync {
    Long accountCount;
    Long goalCount;
    Long goalArchiveCount;

    public Map<String, Object> toData() {
        Map<String, Object> docData = new HashMap<>();
        docData.put("account_count", accountCount);
        docData.put("goal_count", goalCount);
        docData.put("goal_archive_count", goalArchiveCount);
        return docData;
    }
}
