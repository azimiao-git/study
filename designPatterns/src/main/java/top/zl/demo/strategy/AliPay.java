package top.zl.demo.strategy;

/**
 * @author zl
 * 2021/08/17
 */
public class AliPay extends Payment{
    @Override
    protected void checkParam(Object dto) {
        System.out.println("阿里支付渠道，校验参数");
    }

    @Override
    protected void initParam(Object dto) {
        System.out.println("阿里支付渠道，组装支付参数");
    }

    @Override
    protected void execute(Object dto) {
        System.out.println("阿里支付渠道，发起支付请求");
    }
}
