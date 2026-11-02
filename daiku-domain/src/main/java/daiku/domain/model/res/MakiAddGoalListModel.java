package daiku.domain.model.res;

import daiku.domain.enums.UpdatingFlg;
import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;

@Data
@Entity
public class MakiAddGoalListModel {
    private Long id;
    private LocalDate createDate;
    private Long accountId;
    private String createdAccountFamilyName;
    private String createdAccountGivenName;
    private String createdAccountImg;
    private String title;
    private String purpose;
    private String aim;
    private LocalDate dueDate;
    private Long favoriteId;
    private Long archiveId;
    private LocalDate archivesCreateDate;
    private UpdatingFlg updatingFlg;
    private Long makiRelationId;
    private Long sort_num;
    private String makiKey;
}
