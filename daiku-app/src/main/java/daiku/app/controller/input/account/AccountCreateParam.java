package daiku.app.controller.input.account;

import daiku.app.service.input.account.AccountCreateServiceInput;
import daiku.app.service.input.account.AccountUpdateServiceInput;
import daiku.domain.entity.TAccounts;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@Builder
public class AccountCreateParam {
    @NotNull
    @Size(max = 100)
    String familyName;
    @NotNull
    @Size(max = 100)
    String givenName;
    @Size(max = 100)
    String nickName;

    public AccountCreateServiceInput toService(TAccounts account) {
        return AccountCreateServiceInput.builder()
                .uid(account.getUid())
                .familyName(familyName)
                .givenName(givenName)
                .nickName(nickName)
                .email(account.getEmail())
                .build();
    }

    public AccountUpdateServiceInput toUpdateService(TAccounts account) {
        return AccountUpdateServiceInput.builder()
                .accountId(account.getId())
                .uid(account.getUid())
                .familyName(familyName)
                .givenName(givenName)
                .nickName(nickName)
                .build();
    }
}
