package daiku.app.service.input.account;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountCreateServiceInput {
    String uid;
    String familyName;
    String givenName;
    String nickName;
    String email;
}
