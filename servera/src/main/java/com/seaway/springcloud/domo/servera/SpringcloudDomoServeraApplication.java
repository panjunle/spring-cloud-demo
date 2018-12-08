package com.seaway.springcloud.domo.servera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@MapperScan("com.seaway.springcloud.domo.servera")
@EnableDiscoveryClient
public class SpringcloudDomoServeraApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDomoServeraApplication.class, args);
    }
}
