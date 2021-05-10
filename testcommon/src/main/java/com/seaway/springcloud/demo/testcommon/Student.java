package com.seaway.springcloud.demo.testcommon;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 9038027818888536950L;

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
