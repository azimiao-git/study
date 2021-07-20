package top.zl.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这个规则被@ComponentScan扫描到 会应用所有的服务
 *
 * @author zl
 * 2021/07/20
 */
@Configuration
public class MyRuleConfig {

    @Bean
    public IRule rule() {
        return new RandomRule();
    }

}
