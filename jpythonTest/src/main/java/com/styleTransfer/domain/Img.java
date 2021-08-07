package com.styleTransfer.domain;

import java.io.Serializable;

public class Img implements Serializable {

    private static final long serialVersionUID = 8990798455744465533L;

    private String name;
    private String path;

    public Img(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
