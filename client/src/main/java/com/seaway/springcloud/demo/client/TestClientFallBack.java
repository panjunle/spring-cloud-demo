package com.seaway.springcloud.demo.client;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestClientFallBack implements TestClient{
    @Override
    public String get(String a, String b) {
        return null;
    }

    @Override
    public String post(Map<String, String> map) {
        return null;
    }

    @Override
    public String hyTest() {
        System.out.println("进入hy fallback");
        return "hy fall back.";
    }

    @Override
    public String getIp() {
        return null;
    }
}
