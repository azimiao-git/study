package top.zl.order.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.zl.common.vo.ResultMsg;
import top.zl.order.entity.Payment;

import javax.annotation.Resource;

/**
 * @author zl
 * 2021/07/16
 */
@RestController
public class OrderController {

    //private static final String PAYMENT_URL = "http://localhost:8081/";

    /**
     * 根据服务名来调用 对应的服务  负载均衡 加上@LoadBalanced注解才可以使用
     */
    //private static final String PAYMENT_URL = "http://PAYMENT-SERVICE/";

    private static final String PAYMENT_URL = "http://payment-service/";

    @Resource
    private RestTemplate restTemplate;

   /* @GetMapping("consumer/create")
    public ResultMsg<Boolean> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"payment/create",payment,ResultMsg.class);
    }*/

    @GetMapping("consumer/zk")
    public String create(Payment payment){
        return restTemplate.getForObject(PAYMENT_URL+"payment/zk",String.class);
    }


    @GetMapping("consumer/get/{id}")
    public ResultMsg<Boolean> get(@PathVariable Integer id){
        return restTemplate.getForObject(PAYMENT_URL+"payment/get/{0}",ResultMsg.class,id);
    }

}
