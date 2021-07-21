package top.zl.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.zl.order.feign.PaymentFeignClient;

/**
 * @author zl
 * 2021/07/21
 */
@RestController
public class ConsumerFeignController {


    @Qualifier("top.zl.order.feign.PaymentFeignClient")
    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @GetMapping("consumer/feign/ok")
    public String feignOk() {
        return paymentFeignClient.feignOk();
    }

    @GetMapping("consumer/feign/timeout/{sleep}")
    /*@HystrixCommand(fallbackMethod = "feignTimeoutHandel", commandProperties = {
            //限制当前线程调用时间峰值
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    public String feignTimeout(@PathVariable Long sleep) {
        //int i = 10 / 0;
        return paymentFeignClient.feignTimeout(sleep);
    }

    public String feignTimeoutHandel(Long sleep) {
        return Thread.currentThread().getName() + "\t" + "80 \t o(╥﹏╥)o";
    }
}
