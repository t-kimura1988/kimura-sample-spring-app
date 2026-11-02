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
public enum ProcessStatus implements CodeEnum<ProcessStatus> {
    NEW("0", "新規"),
    DOING("1", "対応"),
    PROBLEM("2", "問題"),
    COMPLETE("3", "完了");

    @Getter
    private final String value;
    @Getter
    private final String name;

    private static final Map<String, ProcessStatus> valueMap;

    static {
        valueMap = CodeEnumUtil.initEnumMap(ProcessStatus.class);
    }

    public static ProcessStatus of(String value) { return valueMap.get(value); }

    @JsonCreator
    public static ProcessStatus fromString(String value) {
        return valueMap.get(value);
    }
}
