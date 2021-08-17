package top.zl.demo.strategy;

/**
 * @author zl
 * 2021/08/17
 */
public class PayController {


    public Boolean pay(String type){
        PaymentFactory.getPayment(type).pay(type);
        return Boolean.TRUE;
    }

    public static void main(String[] args) {
        PayController controller = new PayController();
        controller.pay("aliPay");
    }
}
