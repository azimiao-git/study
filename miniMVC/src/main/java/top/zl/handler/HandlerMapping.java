package top.zl.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @author zl
 * 2021/08/26
 */
@Data
@AllArgsConstructor
public class HandlerMapping {

    private Object target;

    private Method method;

    private String uri;


}
