package daiku.domain.model.res;

import lombok.Data;
import org.seasar.doma.Entity;

@Data
@Entity
public class IdeaSearchModel {
    private Long id;
    private Long accountId;
    private String createdAccountFamilyName;
    private String createdAccountGivenName;
    private String createdAccountImg;
    private String body;
    private Long storyId;
    private String title;
    private String storyBody;
}
