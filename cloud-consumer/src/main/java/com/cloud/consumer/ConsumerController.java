package com.cloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@RestController
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;


    @GetMapping("consumer")
    public String consumer() {
        return consumerService.consumer();
    }
}
