package top.zl.convert;

/**
 * @author zl
 * 2021/08/26
 */
public class StringToInteger implements Converter<String,Integer>{

    @Override
    public Integer convert(String source) {
        return Integer.parseInt(source);
    }
}
