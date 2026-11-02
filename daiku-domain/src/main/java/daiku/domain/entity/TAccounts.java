package daiku.domain.entity;

import daiku.domain.enums.DelFlg;
import daiku.domain.listener.TAccountsListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = TAccountsListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_accounts")
public class TAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_accounts_id_seq")
    private Long id;

    private String uid;

    private String familyName;

    private String givenName;

    private String nickName;

    private String userImage;

    private String email;

    private String tel;

    private String postNum;

    private DelFlg delFlg;

    private LocalDate birthDay;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long updatedBy;

    private String profileBackImage;

}
