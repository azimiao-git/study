package top.zl.payment.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String feignOk(){
        return "port:"+value+"\t O(∩_∩)O哈哈~";
    }

    @GetMapping("payment/feign/timeout")
    @HystrixCommand(fallbackMethod = "feignTimeoutHandel")
    public String feignTimeout(){
        try { TimeUnit.MILLISECONDS.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        return Thread.currentThread().getName()+"\t"+"O(∩_∩)O哈哈~";
    }

    public String feignTimeoutHandel(){
        return Thread.currentThread().getName()+"\t"+"o(╥﹏╥)o";
    }

}
