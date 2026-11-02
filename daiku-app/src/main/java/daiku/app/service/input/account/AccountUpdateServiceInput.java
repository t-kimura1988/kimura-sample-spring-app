package daiku.app.service.input.account;

import daiku.domain.entity.TAccounts;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountUpdateServiceInput {
    Long accountId;
    String uid;
    String familyName;
    String givenName;
    String nickName;
    String email;

    public TAccounts toRepo() {
        TAccounts accounts = new TAccounts();
        accounts.setId(accountId);
        accounts.setUid(uid);
        accounts.setEmail(email);
        accounts.setFamilyName(familyName);
        accounts.setGivenName(givenName);
        accounts.setNickName(nickName);
        return accounts;
    }


}
