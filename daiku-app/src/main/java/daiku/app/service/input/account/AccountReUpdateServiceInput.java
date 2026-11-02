package daiku.app.service.input.account;

import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.TAccounts;
import daiku.domain.enums.DelFlg;
import lombok.Builder;
import lombok.Value;

import java.util.LinkedHashMap;
import java.util.Map;

@Value
@Builder
public class AccountReUpdateServiceInput {
    TAccounts account;

    public TAccounts toRepo() throws GoenNotFoundException {
        if(account.getDelFlg() == DelFlg.NOT_DELETED) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("uid: ", account.getUid());
            throw  new GoenNotFoundException("account is not deleted", param);
        }
        account.setDelFlg(DelFlg.NOT_DELETED);
        return account;
    }
}
