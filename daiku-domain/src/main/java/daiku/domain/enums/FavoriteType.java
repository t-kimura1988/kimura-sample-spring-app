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
public enum FavoriteType implements CodeEnum<FavoriteType> {
    GOALS("1", "t_goals"),
    ACCOUNT("2", "t_accounts");

    @Getter
    private final String value;
    @Getter
    private final String name;

    private static final Map<String, FavoriteType> valueMap;

    static {
        valueMap = CodeEnumUtil.initEnumMap(FavoriteType.class);
    }

    public static FavoriteType of(String value) { return valueMap.get(value); }

    @JsonCreator
    public static FavoriteType fromString(String value) {
        return valueMap.get(value);
    }
}
