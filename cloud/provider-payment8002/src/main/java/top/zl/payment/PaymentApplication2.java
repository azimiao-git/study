package top.zl.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zl
 * 2021/07/14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication2.class);
    }
}
