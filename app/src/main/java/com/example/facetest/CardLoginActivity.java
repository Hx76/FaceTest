package com.example.facetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralBasicParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.WordSimple;
import com.example.faceget.R;
import com.example.facetest.datebase.StudentInfo;
import com.example.facetest.utils.DisposeStr;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class CardLoginActivity extends AppCompatActivity {
    private boolean hasGotToken = false;
    private Button btn;
    private TextView tv;
    private Button login;
    GeneralBasicParams param = new GeneralBasicParams();
    private String mFilePaths;
    private Uri photoUri;
    private String str1 = "";
    DisposeStr disposeStr = new DisposeStr();
    List<StudentInfo> name_sql;
    List<StudentInfo> major_sql;
    private String name;
    private String major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_login);

        btn = findViewById(R.id.btn);
        login = findViewById(R.id.c_login);
        tv = findViewById(R.id.tv);

        mFilePaths = Environment.getExternalStorageDirectory().getPath();
        mFilePaths = mFilePaths + "/" + "face.jpg";
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardLoginActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoUri = Uri.fromFile(new File(mFilePaths));
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                intent.putExtra("android.intent.extras.CAMERA_FACING", 2);
                startActivityForResult(intent, 1);
            }
        });

        initAccessTokenWithAkSk();
    }

    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public void Currency(){
        final StringBuffer sb=new StringBuffer();
        // 通用文字识别参数设置
        GeneralBasicParams param = new GeneralBasicParams();
        param.setDetectDirection(true);
        final String str=getRealFilePath(this,photoUri);
        Log.e("TGA",str+"------str-------------");
        param.setImageFile(new File(getRealFilePath(this,photoUri)));

// 调用通用文字识别服务
        OCR.getInstance(this).recognizeGeneralBasic(param, new OnResultListener<GeneralResult>() {
            @Override
            public void onResult(GeneralResult result) {
                // 调用成功，返回GeneralResult对象
                for (WordSimple wordSimple : result.getWordList()) {
                    // wordSimple不包含位置信息
                    sb.append(wordSimple.getWords());
                    sb.append("\n");
                }
                String strs = disposeStr.findID(sb.toString());
                Log.d("CardLoginActivity.this", "123123"+strs);
             //  String strs = "20171103187";
                name_sql = LitePal.select("Student_Name").where("Student_Number = ?",strs).find(StudentInfo.class);
                major_sql = LitePal.select("Major").where("Student_Number = ?",strs).find(StudentInfo.class);
                for (StudentInfo userInformation : name_sql) {
                    name = userInformation.getStudent_Name();
                }
                for (StudentInfo userInformation : major_sql) {
                    major = userInformation.getMajor();
                }
                tv.setText(name + major);

                /*String str = "";
                str = sb.toString();
                str1 = str;
                str1 = change(str1);
                tv.setText(str1);*/
                // json格式返回字符串
//                listener.onResult(result.getJsonRes());
            }
            @Override
            public void onError(OCRError error) {
                // 调用失败，返回OCRError对象
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            FileInputStream fis = null;
            if (requestCode == 1) {
                try {
                    fis = new FileInputStream(mFilePaths);
                    //final Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    Currency();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String change(String str){
        String[] s=str.split("\n");
        for(int i=0;i<s.length;i++){
            System.out.println(s[i]);
        }
        return str;
    }


    private void initAccessTokenWithAkSk() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                hasGotToken = true;
            }
            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                //        alertText("AK，SK方式获取token失败", error.getMessage());
            }
        }, getApplicationContext(),  "hxzLzAdtuq2P2e93dozyjRae", "1aih7Bdm4BiMywbHOFa98WNlIp7aNEeT");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放内存资源
        OCR.getInstance(this).release();
    }
}
