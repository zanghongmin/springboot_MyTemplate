package top.zang.config.token;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Documented
@Inherited
public @interface RequestToken {
    boolean required() default true;
}
