package top.zl.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 同一返回
 *
 * @author zl
 * 2021/07/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMsg<T> {

    private Integer code;
    private String message;

    private T data;

    public ResultMsg(Integer code, String message) {
        this(code, message, null);
    }

    public static <T> ResultMsg<T> success(T data){
        return new ResultMsg<>(200,"操作成功",data);
    }

    public static <T> ResultMsg<T> fail(String message){
        return new ResultMsg<>(500,message,null);
    }
}
