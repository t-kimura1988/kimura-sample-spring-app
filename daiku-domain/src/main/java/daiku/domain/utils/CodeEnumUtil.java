package daiku.domain.utils;

import daiku.domain.enums.CodeEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CodeEnumUtil {

    public static <E extends Enum<E> & CodeEnum> Map<String, E> initEnumMap(Class<E> targetClass) {
        Map<String, E> map = new HashMap<>();
        Arrays.stream(targetClass.getEnumConstants())
                .forEach(e -> map.put(e.getValue(), e));
        return Collections.unmodifiableMap(map);
    }
}
