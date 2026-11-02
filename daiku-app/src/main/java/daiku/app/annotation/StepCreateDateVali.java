package daiku.app.annotation;

import daiku.app.validation.StepCreateDateValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 日付が未来日かチェック（現在日は正常）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = StepCreateDateValidation.class)
public @interface StepCreateDateVali {
    String message() default "{error.argument.step_create_date_is_today}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Documented
    @interface list {
        StepCreateDateVali value();
    }
}
