package com.cloud.consumer;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate (){
        return new RestTemplate() ;
    }
    @Bean
    public IRule getIRule (){
        // 默认轮询算法
        // return new RoundRobinRule() ;
        // 重试算法:默认情况，访问某个服务连续三次失败，就不会再访问
        // return new RetryRule() ;
        // 随机算法
        return new RandomRule() ;
    }

}
