package com.seaway.springcloud.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "servera",fallback = TestClientFallBack.class)
public interface TestClient {

    @RequestMapping("/getTest")
    String get(@RequestParam String a, @RequestParam String b);

    @RequestMapping("/postTest")
    String post(Map<String, String> map);

    @RequestMapping(value = "/hy",method = RequestMethod.GET)
    String hyTest();

    @RequestMapping(value = "/ip",method = RequestMethod.GET)
    String getIp();
}