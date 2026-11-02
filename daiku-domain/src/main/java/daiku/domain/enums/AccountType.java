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
public enum AccountType implements CodeEnum<AccountType> {
    GENERAL("0", "一般");

    @Getter
    private final String value;
    @Getter
    private final String name;

    private static final Map<String, AccountType> valueMap;

    static {
        valueMap = CodeEnumUtil.initEnumMap(AccountType.class);
    }

    public static AccountType of(String value) {
        return valueMap.get(value);
    }

    @JsonCreator
    public static AccountType fromString(String value) {
        return valueMap.get(value);
    }
}
