package top.zl.order.failback;

import org.springframework.stereotype.Component;
import top.zl.order.feign.PaymentFeignClient;

/**
 * @author zl
 * 2021/07/21
 */
@Component
public class PaymentFeignFallBack implements PaymentFeignClient {
    @Override
    public String payment() {
        return "payment o(╥﹏╥)o";
    }

    @Override
    public String feignOk() {
        return "feignOk o(╥﹏╥)o";
    }

    @Override
    public String feignTimeout(Long sleep) {
        return "feignTimeout o(╥﹏╥)o";
    }
}
