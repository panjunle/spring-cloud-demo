package com.seaway.springcloud.demo.testcommon;

import java.io.Serializable;

public class Teacher implements Serializable {

    private static final long serialVersionUID = -6268354427076074288L;

    private String name;

    private String office;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
