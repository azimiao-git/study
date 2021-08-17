package top.zl.demo.strategy;

/**
 *支付抽象类
 * @author zl
 * 2021/08/17
 */
public abstract class Payment {

    /**
     * 校验参数
     */
    protected abstract void checkParam(Object dto);

    /**
     * 初始化请求参数
     */
    protected abstract void initParam(Object dto);

    /**
     * 发起支付
     */
    protected abstract void execute(Object dto);

    /**
     * 支付方法
     */
    public void pay(Object dto){
        checkParam(dto);
        initParam(dto);
        execute(dto);
        ///...
    }

    //...其他
}
