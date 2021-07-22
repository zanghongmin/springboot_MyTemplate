package top.zang.config.validator;

import top.zang.core.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 */
public class EnumValidatorClass implements ConstraintValidator<EnumValidator, Object>, Annotation {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private List<Object> values = new ArrayList<>();

    @Override
    public void initialize(EnumValidator enumValidator) {
        Class<?> clz = enumValidator.value();
        Object[] objects = clz.getEnumConstants();
        try {
            Method method = clz.getMethod("getCode");
            if (Objects.isNull(method)) {
                throw new MyException(String.format("枚举对象{}缺少字段名为code的字段",clz.getName()));
            }
            Object value;
            for (Object obj : objects) {
                value = method.invoke(obj);
                values.add(value);
            }
        } catch (Exception e) {
            log.error("枚举类参数校验异常", e);
        }
    }


    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(value) || values.contains(value) ? true : false;
    }

}
