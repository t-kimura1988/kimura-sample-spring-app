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
public enum UpdatingFlg implements CodeEnum<UpdatingFlg> {
    ARCHIVING("0", "達成済"),
    ARCHIVE_UPDATING("1", "更新中");

    @Getter
    private final String value;
    @Getter
    private final String name;

    private static final Map<String, UpdatingFlg> valueMap;

    static {
        valueMap = CodeEnumUtil.initEnumMap(UpdatingFlg.class);
    }

    public static UpdatingFlg of(String value) { return valueMap.get(value); }

    @JsonCreator
    public static UpdatingFlg fromString(String value) {
        return valueMap.get(value);
    }
}
