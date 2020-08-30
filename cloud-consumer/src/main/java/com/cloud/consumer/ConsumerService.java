package com.cloud.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@FeignClient(value = "service-provider", fallback = FallbackService.class)
public interface ConsumerService {

    @GetMapping("client")
    String consumer();

    @GetMapping("zuul")
    String zuul();


}
