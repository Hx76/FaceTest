package com.example.facetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.faceget.R;

public class SetActivity extends AppCompatActivity {
    Button function;
    Button quit;
    Button face_manage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        function = findViewById(R.id.function);
        quit = findViewById(R.id.quit);
        face_manage = findViewById(R.id.face_manage);

        function.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        face_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
