package daiku.app.service.input.account;

import daiku.domain.entity.TAccounts;
import daiku.domain.enums.DelFlg;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountDeleteServiceInput {
    TAccounts account;

    public TAccounts toRepo() {
        account.setDelFlg(DelFlg.DELETED);
        return account;
    }
}
