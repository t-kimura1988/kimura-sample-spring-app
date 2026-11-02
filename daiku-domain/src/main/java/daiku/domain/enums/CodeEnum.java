package daiku.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public interface CodeEnum<E extends Enum<E>> {

    @JsonValue
    String getValue();

    String getName();

    static <E extends CodeEnum> E lookUp(Class<E> enumClass, String code) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(it -> it.getValue().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s does not have such code => [%s]",
                        enumClass.getSimpleName(),
                        code)));
    }

}
