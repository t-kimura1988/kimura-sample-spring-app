package daiku.domain.model.param;

import daiku.domain.enums.DelFlg;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountDaoParam {
    private Long id;
    private DelFlg delFlg;
    private String uid;
    private LocalDateTime updatedAt;
}
