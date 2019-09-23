package com.example.facetest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.faceget.R;
import com.example.facetest.datebase.StudentInfo;

//import org.litepal.LitePal;

import org.litepal.LitePal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Button register;
    private Button login;
    private EditText email;
    private EditText pwd;
    String Email;
    String Pwd;
    List<StudentInfo> pwd_sql;
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        register = findViewById(R.id.card_login);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email_login);
        pwd = findViewById(R.id.password_login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,CardLoginActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Email = email.getText().toString();
                Pwd = pwd.getText().toString();

                if (Email == null || Email.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"请输入学号",Toast.LENGTH_SHORT).show();
                }else {
                    if (Pwd == null || Pwd.isEmpty()) {
                        Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    }else {
                        pwd_sql = LitePal.select("ID_Number").where("Student_Number = ?",Email).find(StudentInfo.class);
                      //  Log.d(TAG, "123"+pwd_sql.get(0));
                        String email1 = null;
                        for (StudentInfo userInformation : pwd_sql) {
                            email1 = userInformation.getID_Number();
                            password = email1.substring(email1.length()-6);
                          //  Log.d(TAG, "456"+password);
                            Log.d(TAG, "789"+Pwd);
                        }
                        if (Pwd.equals(password)) {
                            Intent intent = new Intent(LoginActivity.this,IndexActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this,"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }
        });


    }
}
