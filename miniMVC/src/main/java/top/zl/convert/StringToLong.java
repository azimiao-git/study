package top.zl.convert;

/**
 * @author zl
 * 2021/08/26
 */
public class StringToLong implements Converter<String,Long>{

    @Override
    public Long convert(String source) {
        return Long.parseLong(source);
    }
}
