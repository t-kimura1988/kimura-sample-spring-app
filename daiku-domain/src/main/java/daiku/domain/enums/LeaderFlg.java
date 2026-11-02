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
public enum LeaderFlg implements CodeEnum<LeaderFlg> {
    SUPPORTING("0", "一般役"),
    LEADER("1", "主役");

    @Getter
    private final String value;
    @Getter
    private final String name;

    private static final Map<String, LeaderFlg> valueMap;

    static {
        valueMap = CodeEnumUtil.initEnumMap(LeaderFlg.class);
    }

    public static LeaderFlg of(String value) { return valueMap.get(value); }

    @JsonCreator
    public static LeaderFlg fromString(String value) {
        return valueMap.get(value);
    }
}
