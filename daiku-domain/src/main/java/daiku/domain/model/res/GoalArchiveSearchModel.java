package daiku.domain.model.res;

import daiku.domain.enums.PublishLevel;
import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;

@Data
@Entity
public class GoalArchiveSearchModel {
    private Long id;
    private LocalDate archivesCreateDate;
    private PublishLevel publish;
    private String thoughts;
    private String updatingFlg;
    private Long goalId;
    private LocalDate goalCreateDate;
    private String title;
    private String purpose;
    private String aim;
    private LocalDate dueDate;
    private Long goalCreateAccountId;
    private String familyName;
    private String givenName;
    private String nickName;
    private String userImage;
    private Long processCount;
}
