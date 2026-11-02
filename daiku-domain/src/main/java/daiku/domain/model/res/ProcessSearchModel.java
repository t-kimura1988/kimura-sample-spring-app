package daiku.domain.model.res;

import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;

@Data
@Entity
public class ProcessSearchModel {
    private Long id;
    private Long goalId;
    private Long accountId;
    private LocalDate goalCreateDate;
    private String createdAccountFamilyName;
    private String createdAccountGivenName;
    private String createdAccountImg;
    private String title;
    private String beforeTitle;
    private ProcessPriority beforePriority;
    private ProcessPriority priority;
    private ProcessStatus beforeProcessStatus;
    private ProcessStatus processStatus;
    private String body;
    private String beforeBody;
    private LocalDate beforeProcessStartDate;
    private LocalDate processStartDate;
    private LocalDate beforeProcessEndDate;
    private LocalDate processEndDate;
}
