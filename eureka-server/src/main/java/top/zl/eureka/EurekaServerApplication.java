package top.zl.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zl
 * 2021/07/16
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class);
    }
}
/*
 *  eureka 注册中心
 *      1.调用大致流程
 *          eureka分为客户端和服务端 --> 客户端又可以分为 提供者 和 消费者
 *          1.1 提供者注册到eureka服务端中 (消费者可以不注册)
 *          1.2 消费者根据提供者的服务名称去服务端拉取服务信息 (缓存到本地 默认30s会去服务端更新信息)
 *          1.3 然后根据信息去调用服务  调用方式有：httpClient RestTemplate ribbon openFeign
 *      2.心跳机制
 *          2.1 Eureka客户端 默认每隔30s会给服务端发送一次心跳
 *          2.2 如果服务端 默认90s没有接收到 某个服务的心跳 会认为这个服务挂了 然后会移除这个服务的实例
 *      3.自我保护
 *          3.1 如果在15分钟内超过85%的客户端节点都没有正常的心跳,Eureka会认为出现了网络故障,自动进入保护机制
 *          3.2 eureka server 不在从注册列表移除服务
 *          3.3 eureka server 不会把信息同步到其他节点上,等网络稳定时,才会开始同步信息
 *
 */
