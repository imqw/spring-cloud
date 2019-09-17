package com.cloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@RestController
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("consumer")
    public String consumer() {
        return consumerService.consumer();
    }




    @GetMapping("ribbon")
    public String ribbon() {
        return restTemplate.getForObject("http://eureka-client/ribbon", String.class);
    }

}
