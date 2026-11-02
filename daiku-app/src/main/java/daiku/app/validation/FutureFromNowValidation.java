package daiku.app.validation;

import daiku.app.annotation.FutureFromNow;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * 対象の日付が現在日付より未来または同日は正常、それ以外は不正
 */
@Component
public class FutureFromNowValidation implements ConstraintValidator<FutureFromNow, LocalDate> {

    @Override
    public void initialize(FutureFromNow constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        if(value.isAfter(LocalDate.now()) || value.isEqual(LocalDate.now())) {
            return true;
        }

        return false;
    }
}
