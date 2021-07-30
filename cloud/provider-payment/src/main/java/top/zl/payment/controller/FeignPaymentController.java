package top.zl.payment.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zl
 * 2021/07/21
 */
@RestController
public class FeignPaymentController {


    @Value("${server.port}")
    private Integer value;

    @GetMapping("payment/feign/ok")
    public String feignOk() {
        return "port:" + value + "\t O(∩_∩)O哈哈~";
    }

    @GetMapping("payment/feign/timeout/{sleep}")
    /*@HystrixCommand(fallbackMethod = "feignTimeoutHandel", commandProperties = {
            //限制当前线程调用时间峰值
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })*/
    public String feignTimeout(@PathVariable Long sleep) {
        int i = 10 / 0;
        try { TimeUnit.MILLISECONDS.sleep(sleep); } catch (InterruptedException e) { e.printStackTrace(); }
        return Thread.currentThread().getName() + "\t" + "O(∩_∩)O哈哈~";
    }

    public String feignTimeoutHandel(Long sleep) {
        return Thread.currentThread().getName() + "\t" + "o(╥﹏╥)o";
    }

}
