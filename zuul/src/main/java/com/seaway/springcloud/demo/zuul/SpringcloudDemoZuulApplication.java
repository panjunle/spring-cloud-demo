package com.seaway.springcloud.demo.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableHystrix
public class SpringcloudDemoZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDemoZuulApplication.class, args);
    }

    @Bean
    public MyPostZuulFilter myPostZuulFilter() {
        return new MyPostZuulFilter();
    }

    @Bean
    public MyPreZuulHeader myPreZuulHeader(){
        return new MyPreZuulHeader();
    }
}
