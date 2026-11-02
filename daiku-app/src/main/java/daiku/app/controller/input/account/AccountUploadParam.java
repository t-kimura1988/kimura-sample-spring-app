package daiku.app.controller.input.account;

import daiku.app.service.input.account.AccountUploadServiceInput;
import daiku.domain.entity.TAccounts;
import daiku.domain.enums.AccountImageType;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class AccountUploadParam {
    @NotNull
    @NotEmpty
    String imagePath;
    @NotNull
    AccountImageType imageType;

    public AccountUploadServiceInput toService(TAccounts account) {
        return AccountUploadServiceInput.builder()
                .account(account)
                .imagePath(imagePath)
                .imageType(imageType).build();
    }
}
