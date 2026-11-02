package daiku.app.annotation;

import daiku.app.validation.FutureFromNowValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 日付が未来日かチェック（現在日は正常）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = FutureFromNowValidation.class)
public @interface FutureFromNow {
    String message() default "{error.argument.future_from_now}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Documented
    @interface list {
        FutureFromNow value();
    }
}
