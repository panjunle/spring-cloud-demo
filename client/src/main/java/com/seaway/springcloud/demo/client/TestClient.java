package com.seaway.springcloud.demo.client;

import com.seaway.springcloud.demo.testcommon.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "servera",fallback = TestClientFallBack.class)
public interface TestClient {

    @RequestMapping("/getTest")
    String get(@RequestParam String a, @RequestParam String b);

    @RequestMapping("/postTest")
    String post(Map<String, String> map);

    @PostMapping("/stu")
    Student studentTest(Student stu);

    @PostMapping("/stuList")
    String stuTest(@RequestBody List<Student> stuList);

    @PostMapping("txlcn")
    String serveraTxlcn(@RequestParam("tx") String tx);

}