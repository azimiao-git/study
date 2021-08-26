package top.zl.annotation;

import java.lang.annotation.*;

/**
 * @author zl
 * 2021/08/26
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
