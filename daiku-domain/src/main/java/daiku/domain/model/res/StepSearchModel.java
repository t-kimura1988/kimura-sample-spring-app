package daiku.domain.model.res;

import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;

@Data
@Entity
public class StepSearchModel {
    private Long id;
    private LocalDate createDate;
    private Long accountId;
    private String createdAccountFamilyName;
    private String createdAccountGivenName;
    private String createdAccountImg;
    private String body;
}
