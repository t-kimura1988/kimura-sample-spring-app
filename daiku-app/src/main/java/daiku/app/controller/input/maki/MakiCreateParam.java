package daiku.app.controller.input.maki;

import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.groups.UpdateGroups;
import daiku.app.service.input.maki.MakiCreateServiceInput;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class MakiCreateParam {
    @NotNull(groups = UpdateGroups.class)
    Long makiId;
    @NotEmpty(groups = {UpdateGroups.class, CreateGroups.class})
    String makiTitle;
    @NotEmpty(groups = {UpdateGroups.class, CreateGroups.class})
    String makiKey;
    String makiDesc;

    public MakiCreateServiceInput toCreateService(Long accountId) {
        return MakiCreateServiceInput.builder()
                .makiTitle(makiTitle)
                .makiKey(makiKey)
                .makiDesc(makiDesc)
                .accountId(accountId).build();
    }
}
