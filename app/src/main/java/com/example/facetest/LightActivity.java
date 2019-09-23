package com.example.facetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.faceget.R;
import com.example.facetest.datebase.LightInfo;
import com.example.facetest.datebase.RelayInfo;
import com.example.facetest.datebase.StudentInfo;


import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LightActivity extends AppCompatActivity {
    private Button light;
    // 主线程Handler
    // 用于将从服务器获取的消息显示出来
    private Handler mMainHandler;
    private int[] num;
    // Socket变量
    private Socket socket;
    // 线程池
    // 为了方便展示,此处直接采用线程池进行线程管理,而没有一个个开线程
    private ExecutorService mThreadPool;
    private ExecutorService mThreadPool1;
    // 输入流对象
    InputStream is;
    // 输入流读取器对象
    InputStreamReader isr ;
    BufferedReader br ;
    // 接收服务器发送过来的消息
    String response;
    // 输出流对象
    OutputStream outputStream;
    OutputStream outputStream1;
    public String b=null;
    List<LightInfo> relay_id_sql;
    List<RelayInfo> cmd_open_sql;
    private String cmd_open;
    private String relay_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        num = new int[10];
        light = findViewById(R.id.light);
        relay_id_sql = LitePal.select("Relay_id").where("class_id = ? and light_id = ?","A209","1").find(LightInfo.class);
        for (LightInfo lightInfo : relay_id_sql) {
            relay_id = lightInfo.getRelay_id();
        }
        cmd_open_sql = LitePal.select("Open_cmd").where("Relay_ID = ?",relay_id).find(RelayInfo.class);
        for (RelayInfo relayInfo : cmd_open_sql) {
            cmd_open = relayInfo.getOpen_cmd();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建Socket对象 & 指定服务端的IP 及 端口号
                    socket = new Socket("192.168.1.196", 68);
                    // 判断客户端和服务器是否连接成功
                    System.out.println(socket.isConnected());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        // 初始化线程池
        mThreadPool = Executors.newCachedThreadPool();
        mThreadPool1 = Executors.newCachedThreadPool();
        // 实例化主线程,用于更新接收过来的消息
        mMainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
            }
        };

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 利用线程池直接开启一个线程 & 执行该线程
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            // 步骤1：从Socket 获得输出流对象OutputStream
                            // 该对象作用：发送数据
                            outputStream = socket.getOutputStream();
                            Log.d("MainActivity.this","\n");

                            // 步骤2：写入需要发送的数据到输出流对象中
                            outputStream.write(Init("61 64 6D 69 6E 0D 0A"+"\n"));
                            outputStream.flush();
                            outputStream.write(Init(cmd_open));
                            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                            //55aa040002010108
                            //61 64 6D 69 6E
                            outputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

                // 利用线程池直接开启一个线程 & 执行该线程
              /*  */
            }
        });
    }



    public byte[] Init(String str) {
        str=str.replaceAll(" ", "");
        int len= str.length();
        int num = len / 2;
        byte[] out = new byte[num];
        for (int i = 0; i < num; i++) {
            int value = Integer.valueOf(str.substring(i * 2, 2 * (i + 1)), 16);//按字符串2个字符为一个字节截取并转化为16进制整型
            out[i] = (byte) value;
        }
        return out;
    }
}
