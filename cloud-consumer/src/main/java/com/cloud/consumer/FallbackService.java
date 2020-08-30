package com.cloud.consumer;

import org.springframework.stereotype.Component;

/**
 * feign的熔断方法
 *
 * @Author: qiuwei@19pay.com.cn
 * @Date: 2019-12-06.
 */
@Component
public class FallbackService implements ConsumerService {
    @Override
    public String consumer() {
        return "Feign的熔断方法";
    }

    @Override
    public String zuul() {
        return "请求provider服务异常";
    }
}
