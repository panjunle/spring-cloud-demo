package com.seaway.springcloud.demo.client;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@MapperScan("com.seaway.springcloud.demo.dao")
@EnableDistributedTransaction
//@RibbonClient(name = "SERVERA",configuration = CuRuleCfg.class)
public class SpringcloudDemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDemoClientApplication.class, args);
    }
}
