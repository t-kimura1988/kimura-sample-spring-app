package daiku.domain.utils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;

public class CollectorUtil {
    private CollectorUtil() {}
    public static <T> Collector<T, ?, Optional<T>> toOptional() {
        return Collector.<T, AtomicReference<T>, Optional<T>>of(
                AtomicReference::new,
                (acc, t) -> acc.compareAndSet(null, t),
                (a, b)   -> {a.compareAndSet(null, b.get()); return a;},
                acc      -> Optional.ofNullable(acc.get()),
                Collector.Characteristics.CONCURRENT
        );
    }
}
