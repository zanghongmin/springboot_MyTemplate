package top.zang.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = EnumValidatorClass.class)
public @interface EnumValidator {

    Class<?> value();

    String message() default "入参值不在正确枚举中";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
