package com.cloud.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
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

    private static int NUM = 1;

    @Autowired
    ConsumerService consumerService;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("consumer")
    public String consumer() {
        return consumerService.consumer();
    }


    @GetMapping("ribbon")
    @HystrixCommand(fallbackMethod = "getRibbonDefaultInfo")
    public String ribbon() throws InterruptedException {
        String result = "";
        //定义了一个常量来模拟 超时请求和正常请求
        if (NUM % 2 == 0) {
            NUM += 1;
            System.out.println("线程模拟熔断 睡眠3秒");
            /**
             * 熔断的默认配置是1s没有响应就算失败  走熔断方法
             */
            Thread.sleep(3000);
        } else {
            NUM += 1;
            result = restTemplate.getForObject("http://service-provider/ribbon", String.class);
        }
        System.out.println("当前请求次数 NUM:" + NUM);
        return result;
    }


    @GetMapping
    public String zuul(){

        return this.consumerService.zuul();
    }



    /**
     * 熔断方法
     * ribbon
     */

    public String getRibbonDefaultInfo() {
        return "ribbon 类型 服务熔断";
    }


}
