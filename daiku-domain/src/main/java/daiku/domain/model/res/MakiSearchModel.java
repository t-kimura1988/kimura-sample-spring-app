package daiku.domain.model.res;

import lombok.Data;
import org.seasar.doma.Entity;

@Data
@Entity
public class MakiSearchModel {
    private Long id;
    private Long accountId;
    private String createdAccountFamilyName;
    private String createdAccountGivenName;
    private String createdAccountImg;
    private String makiTitle;
    private String makiKey;
    private String makiDesc;
}
