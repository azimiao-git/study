package top.zl.annotation;

import java.lang.annotation.*;

/**
 * @author zl
 * 2021/08/24
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
