package top.zl.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.zl.order.failback.PaymentFeignFailBackFactory;

/**
 * @author zl
 * 2021/07/21
 */
@FeignClient(value = "payment-service",fallbackFactory = PaymentFeignFailBackFactory.class)
public interface PaymentFeignClient {

    @GetMapping("/payment/zk")
    public String payment();

    @GetMapping("payment/feign/ok")
    public String feignOk();

    @GetMapping("payment/feign/timeout/{sleep}")
    public String feignTimeout(@PathVariable Long sleep);
}
