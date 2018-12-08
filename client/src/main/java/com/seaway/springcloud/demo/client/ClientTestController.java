package com.seaway.springcloud.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class ClientTestController {

    @Autowired
    private TestClient testClient;
    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${foo}")
    private String bar;

    @GetMapping(value = "/get")
    public String getTest() {
        List<String> services = discoveryClient.getServices();
        for (String string : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(string);
            System.out.println("服务名称："+string+",服务数量："+instances.size());
        }
        return testClient.get("123","xyzz");
    }

    @PostMapping(value = "/post")
    public String postTest() {
        Map<String,String> map = new HashMap<>(3);
        map.put("test","1.0.0.0");
        map.put("a","213");
        map.put("b","xyzz");
        List<String> services = discoveryClient.getServices();
        for (String string : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(string);
            System.out.println("服务名称："+string+",服务数量："+instances.size());
        }
        return testClient.post(map);
    }

    @GetMapping("/hy")
    public String hy(){
        System.out.println("收到hy请求");

        return testClient.hyTest();
    }

    @GetMapping(value = "/discoveryClient")
    public String countService(){
        List<String> services = discoveryClient.getServices();
        for (String string : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(string);
            System.out.println("服务名称："+string+",服务数量："+instances.size());
        }
        return "success";
    }

    @GetMapping("/cf")
    public String testConfig(@Value("${foo}")String foo){
        System.out.println(foo);
        return foo;
    }

    @GetMapping("/cf1")
    public String testConfig1(){
        System.out.println(bar);
        return bar;
    }

    @GetMapping("/ip")
    public String getIp(){
        return testClient.getIp();
    }
}


