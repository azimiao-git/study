package top.zl.order.failback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import top.zl.order.feign.PaymentFeignClient;

import javax.annotation.Resource;

/**
 * @author zl
 * 2021/07/21
 */
@Component
public class PaymentFeignFailBackFactory implements FallbackFactory<PaymentFeignClient> {

    @Resource
    private PaymentFeignFallBack paymentFeignFallBack;

    @Override
    public PaymentFeignClient create(Throwable throwable) {
        //throwable.printStackTrace();
        return paymentFeignFallBack;
    }
}
