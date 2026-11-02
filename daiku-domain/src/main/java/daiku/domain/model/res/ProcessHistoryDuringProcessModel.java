package daiku.domain.model.res;

import daiku.domain.enums.UpdatingFlg;
import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;

@Data
@Entity
public class ProcessHistoryDuringProcessModel {
    private String id;
    private Long goalId;
    private LocalDate goalCreateDate;
    private Long processId;
    private Long processHistoryId;
    private String createdAccountFamilyName;
    private String createdAccountGivenName;
    private String createdAccountImg;
    private String goalTitle;
    private String processTitle;
    private String processBody;
    private LocalDate processStartDate;
    private LocalDate processEndDate;
    private Long archiveId;
    private LocalDate archivesCreateDate;
    private UpdatingFlg updatingFlg;
}
