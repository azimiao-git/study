package top.zl.v1.convert;


/**
 * 类型装换器
 * @param <S> 源类型
 * @param <T> 目标类型
 */
public interface Converter<S, T> {

	T convert(S source);

}