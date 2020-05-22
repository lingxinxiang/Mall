package com.example.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mall.Bean.User;
import com.example.mall.MainActivity;
import com.example.mall.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LonginActivity extends AppCompatActivity {
    private EditText mEdUsername, mEdpassword;
    private Button mBtLongin,mBtZhuce;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longin);
        mEdUsername = findViewById(R.id.et_username);
        mEdpassword = findViewById(R.id.et_password);
        mBtLongin = findViewById(R.id.bt_login);
        mBtZhuce=findViewById(R.id.bt_zhuec);

        mBtLongin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.setUsername(mEdUsername.getText().toString().trim());
                user.setPassword(mEdpassword.getText().toString().trim());
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User o, BmobException e) {
                        if (e == null) {
                            Toast.makeText(LonginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LonginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LonginActivity.this, "登陆失败"+e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        //监听注册
        mBtZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LonginActivity.this, RegisterActivity.class));

            }
        });
    }
}
