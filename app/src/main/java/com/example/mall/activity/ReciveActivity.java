package com.example.mall.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mall.R;

public class ReciveActivity extends AppCompatActivity {
    private TextView useranme,content,time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive);
        initView();
        initData();


    }

    private void initData() {


    }

    private void initView() {
        useranme=findViewById(R.id.username);
        content=findViewById(R.id.content);
        time=findViewById(R.id.time);
    }
}
