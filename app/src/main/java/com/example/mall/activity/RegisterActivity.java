package com.example.mall.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mall.Bean.User;
import com.example.mall.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEdUsername, mEdPassword, mEdNicknames;
    private Button mBtRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEdUsername = findViewById(R.id.et_username);
        mEdPassword = findViewById(R.id.et_password);
        mEdNicknames = findViewById(R.id.et_nicknames);
        mBtRegister = findViewById(R.id.bt_register);


        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setUsername(mEdUsername.getText().toString().trim());
                user.setPassword(mEdPassword.getText().toString().trim());
                user.setNickname(mEdNicknames.getText().toString().trim());

                if (mEdUsername.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "用户名没有输入", Toast.LENGTH_SHORT).show();
                }else if (mEdPassword.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "密码没有输入", Toast.LENGTH_SHORT).show();
                }else {
                    user.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e==null){
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }


            }
        });
    }
}
