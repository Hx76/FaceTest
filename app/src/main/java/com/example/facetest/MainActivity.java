/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.example.facetest;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.faceget.R;
import com.example.facetest.datebase.ClassInfo;
import com.example.facetest.datebase.DoorInfo;
import com.example.facetest.datebase.LightInfo;
import com.example.facetest.datebase.RelayInfo;
import com.example.facetest.datebase.StudentInfo;

import org.litepal.tablemanager.Connector;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connector.getDatabase();
        Log.d("MainActivity.this", "onCreate: 数据库创建成功");

      /*  StudentInfo studentInfo = new StudentInfo();
        studentInfo.setMajor("17软件工程");
        studentInfo.updateAll("Student_Name = ?","李东伟");
        studentInfo.save();*/

       /* ClassInfo classInfo = new ClassInfo();
        classInfo.setClass_id("A209");
        classInfo.save();
       /* classInfo.setClass_id("B1201");
        classInfo.save();*/

       /* DoorInfo doorInfo = new DoorInfo();
        doorInfo.setDoor_id("1");
        doorInfo.setClass_id("A209");
        doorInfo.setCmd("123");
        doorInfo.save();
       /* doorInfo.setDoor_id("1");
        doorInfo.setClass_id("B1201");
        doorInfo.setCmd("123");
        doorInfo.save();*/

       /* LightInfo lightInfo = new LightInfo();
        lightInfo.setLight_info("1");
        lightInfo.setClass_info("A209");
        lightInfo.setCmd("123");
       // lightInfo.save();
       /*lightInfo.setLight_info("2");
        lightInfo.setClass_info("A209");
        lightInfo.setCmd("123");
      //  lightInfo.save();
      /*  lightInfo.setLight_info("1");
        lightInfo.setClass_info("B1201");
        lightInfo.setCmd("123");
        lightInfo.save();
       /* lightInfo.setLight_info("2");
        lightInfo.setClass_info("B1201");
        lightInfo.setCmd("123");
        lightInfo.save();*/

       /* LightInfo lightInfo = new LightInfo();
        lightInfo.setLight_info("1");
        lightInfo.setClass_info("A209");
        lightInfo.setRelay_id("1");
        lightInfo.save();*/

        RelayInfo relayInfo =new RelayInfo();
        relayInfo.setRelay_ID("1");
        relayInfo.setOpen_cmd("55AA00040002010108\n");
        relayInfo.setClose_cmd("55AA00040001010107\n");
        relayInfo.save();


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        };
        timer.schedule(task, 1500);


    }
}
