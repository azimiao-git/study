package top.zl.v1.annotation;

import java.lang.annotation.*;

/**
 * @author zl
 * 2021/08/23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller{
    String value() default "";
}
