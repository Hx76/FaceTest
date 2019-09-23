package com.example.facetest.datebase;

import org.litepal.crud.LitePalSupport;

public class DoorInfo extends LitePalSupport {
    private String door_id;
    private String class_id;

    public String getDoor_id() {
        return door_id;
    }

    public void setDoor_id(String door_id) {
        this.door_id = door_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    private String cmd;
}
