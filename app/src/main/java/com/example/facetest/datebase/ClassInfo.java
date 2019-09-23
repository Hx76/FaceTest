package com.example.facetest.datebase;

import org.litepal.crud.LitePalSupport;

public class ClassInfo extends LitePalSupport {
    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    private String class_id;
}
