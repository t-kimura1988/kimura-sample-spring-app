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
public enum PublishLevel implements CodeEnum<PublishLevel> {
    OWN("0", "自分のみ"),
    ONLY_GOAL("1", "目標のみ"),
    ALL("2", "全体公開"),;

    @Getter
    private final String value;
    @Getter
    private final String name;

    private static final Map<String, PublishLevel> valueMap;

    static {
        valueMap = CodeEnumUtil.initEnumMap(PublishLevel.class);
    }

    public static PublishLevel of(String value) { return valueMap.get(value); }

    @JsonCreator
    public static PublishLevel fromString(String value) {
        return valueMap.get(value);
    }

}
