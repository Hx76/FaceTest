package com.example.facetest.datebase;

import org.litepal.crud.LitePalSupport;

public class LightInfo extends LitePalSupport {
    private String light_id;

    public String getLight_info() {
        return light_id;
    }

    public void setLight_info(String light_info) {
        this.light_id = light_info;
    }

    public String getClass_info() {
        return class_id;
    }

    public void setClass_info(String class_info) {
        this.class_id = class_info;
    }


    private String class_id;

    public String getLight_id() {
        return light_id;
    }

    public void setLight_id(String light_id) {
        this.light_id = light_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getRelay_id() {
        return Relay_id;
    }

    public void setRelay_id(String relay_id) {
        Relay_id = relay_id;
    }

    private String Relay_id;
}
