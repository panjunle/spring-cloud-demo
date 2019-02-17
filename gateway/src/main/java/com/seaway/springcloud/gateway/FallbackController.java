package com.seaway.springcloud.gateway;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallbackController {

    @RequestMapping(value = "/fallback",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object fallback(){
        System.out.println("enter fallback");
        Map<String,String> ret = new HashMap<>(2);
        ret.put("retCode","9999");
        ret.put("retMsg","fallback");
        return ret;
    }
}
