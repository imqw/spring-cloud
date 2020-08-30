package com.cloud.eureka;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 关闭csrf
        http.csrf().ignoringAntMatchers("/eureka/**");//让csrf忽略该路径的请求
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); // 开启认证
    }
}
