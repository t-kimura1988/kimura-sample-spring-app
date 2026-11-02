package daiku.domain.model.res;

import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;

@Data
@Entity
public class GoalFavoriteSearchModel {
    private Long id;
    private Long goalId;
    private Long accountId;
    private Long archiveId;
    private LocalDate archivesCreateDate;
    private LocalDate favoriteAddDate;
    private LocalDate goalCreateDate;
    private String goalCreatedAccountFamilyName;
    private String goalCreatedAccountGivenName;
    private String goalCreatedAccountNickName;
    private String goalCreatedAccountImg;
    private String title;
    private String purpose;
    private LocalDate dueDate;
    private String favoriteAddAccountFamilyName;
    private String favoriteAddAccountGivenName;
    private String favoriteAddAccountNickName;
    private String favoriteAddAccountImg;
}
