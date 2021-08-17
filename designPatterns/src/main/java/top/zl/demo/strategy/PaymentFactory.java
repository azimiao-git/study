package top.zl.demo.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 *  策略模式
 * @author zl
 * 2021/08/17
 */
public class PaymentFactory {

    private PaymentFactory(){}

    private static final Map<String, Payment> instances;

    static{
        instances = new HashMap<>();
        instances.put(PaymentKey.ALI_PAY,new AliPay());
        instances.put(PaymentKey.JD_PAY,new JDPay());
    }

    /**
     * 根据类型不同返回 对应的支付实例
     * @param type 支付方式
     *  SpringApplicationContext
     */
    public static Payment getPayment(String type){
        return instances.get(type);
    }

    interface PaymentKey{
        String ALI_PAY="aliPay";
        String JD_PAY="jdPay";
    }
}
