package top.zl.annotation;

import java.lang.annotation.*;

/**
 * @author zl
 * 2021/08/26
 */
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoWired {
}
