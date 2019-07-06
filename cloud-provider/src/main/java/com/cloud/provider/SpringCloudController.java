package com.cloud.provider;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@RestController
public class SpringCloudController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;


    @GetMapping("client")
    public String client() {

        String services = "Services: " + discoveryClient.getServices() + " port :" + port;
        System.out.println(services);
        return services;
    }

}
