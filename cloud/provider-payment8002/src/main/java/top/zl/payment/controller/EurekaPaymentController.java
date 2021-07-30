package top.zl.payment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.zl.common.vo.ResultMsg;
import top.zl.payment.entity.Payment;
import top.zl.payment.serivce.PaymentService;

import javax.annotation.Resource;

/**
 * @author zl
 * 2021/07/16
 */
@RestController
public class EurekaPaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private Integer port;

    @PostMapping("payment/create")
    public ResultMsg<Boolean> create(@RequestBody Payment payment) {
        return new ResultMsg<Boolean>(200,port+"",paymentService.save(payment));
    }

    @GetMapping("payment/get/{id}")
    public ResultMsg<Payment> get(@PathVariable("id") Integer id){
        return new ResultMsg<Payment>(200,port+"",paymentService.getById(id));
    }
}
