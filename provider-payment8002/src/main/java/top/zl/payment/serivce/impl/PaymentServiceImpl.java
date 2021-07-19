package top.zl.payment.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zl.payment.entity.Payment;
import top.zl.payment.mapper.PaymentMapper;
import top.zl.payment.serivce.PaymentService;

/**
 * @author zl
 * 2021/07/15
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
}
