package com.example.facetest.datebase;

import org.litepal.crud.LitePalSupport;

public class RelayInfo extends LitePalSupport {
    public String getRelay_ID() {
        return Relay_ID;
    }

    public void setRelay_ID(String relay_ID) {
        Relay_ID = relay_ID;
    }

    public String getOpen_cmd() {
        return Open_cmd;
    }

    public void setOpen_cmd(String open_cmd) {
        Open_cmd = open_cmd;
    }

    public String getClose_cmd() {
        return Close_cmd;
    }

    public void setClose_cmd(String close_cmd) {
        Close_cmd = close_cmd;
    }

    private String Relay_ID;
    private String Open_cmd;
    private String Close_cmd;

}
