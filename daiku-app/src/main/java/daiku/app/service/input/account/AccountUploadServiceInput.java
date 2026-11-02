package daiku.app.service.input.account;

import daiku.domain.entity.TAccounts;
import daiku.domain.enums.AccountImageType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountUploadServiceInput {
    String imagePath;
    AccountImageType imageType;
    TAccounts account;

    public TAccounts updateData() {

        switch (imageType) {
            case ACCOUNT_MAIN:
                account.setUserImage(imagePath);
                break;
            case ACCOUNT_PROFILE_BACK:
                account.setProfileBackImage(imagePath);
                break;
        }
        return account;
    }
}
