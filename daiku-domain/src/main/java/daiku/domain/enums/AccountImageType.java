package daiku.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import daiku.domain.utils.CodeEnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.Domain;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@Domain(valueType = String.class, factoryMethod = "of")
public enum AccountImageType implements CodeEnum<AccountImageType> {
    ACCOUNT_MAIN("0", "アカウントメインアイコン"),
    ACCOUNT_PROFILE_BACK("1", "プロフィールバック画像");

    @Getter
    private final String value;
    @Getter
    private final String name;

    private static final Map<String, AccountImageType> valueMap;

    static {
        valueMap = CodeEnumUtil.initEnumMap(AccountImageType.class);
    }

    public static AccountImageType of(String value) { return valueMap.get(value); }

    @JsonCreator
    public static AccountImageType fromString(String value) {
        return valueMap.get(value);
    }
}
