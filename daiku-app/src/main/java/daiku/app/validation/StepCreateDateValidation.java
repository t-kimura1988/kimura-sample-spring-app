package daiku.app.validation;

import daiku.app.annotation.StepCreateDateVali;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * 対象の日付が現在日付より未来または同日は正常、それ以外は不正
 */
@Component
public class StepCreateDateValidation implements ConstraintValidator<StepCreateDateVali, LocalDate> {

    @Override
    public void initialize(StepCreateDateVali constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        if(value.isEqual(LocalDate.now())) {
            return true;
        }

        return false;
    }
}
