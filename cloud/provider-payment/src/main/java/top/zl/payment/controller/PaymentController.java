package top.zl.payment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zl
 * 2021/07/19
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/zk")
    public String payment() {
        return "spring cloud with zookeeper:"+port+ "\t"+ UUID.randomUUID();
    }
}
