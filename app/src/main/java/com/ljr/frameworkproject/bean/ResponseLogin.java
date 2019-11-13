package com.ljr.frameworkproject.bean;

public class ResponseLogin {
    public String name;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }
}
