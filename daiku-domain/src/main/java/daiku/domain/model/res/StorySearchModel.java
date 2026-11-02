package daiku.domain.model.res;

import lombok.Data;
import org.seasar.doma.Entity;

@Data
@Entity
public class StorySearchModel {
    private Long id;
    private Long ideaId;
    private Long accountId;
    private String createdAccountFamilyName;
    private String createdAccountGivenName;
    private String createdAccountImg;
    private String title;
    private String body;
    private String ideaBody;
}
