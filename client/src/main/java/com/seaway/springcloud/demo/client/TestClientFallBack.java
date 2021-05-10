package com.seaway.springcloud.demo.client;

import com.seaway.springcloud.demo.testcommon.Student;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Student studentTest(Student stu) {
        Student st = new Student();
        st.setName("fall back");
        st.setAge(0);
        return st;
    }

    @Override
    public String stuTest(List<Student> stuList) {
        return "fallback";
    }

    @Override
    public String serveraTxlcn(String tx) {
        return "txlcn fallback";
    }
}
