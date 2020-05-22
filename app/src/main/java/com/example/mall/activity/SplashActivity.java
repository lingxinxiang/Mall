package com.example.mall.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mall.Bean.User;
import com.example.mall.MainActivity;
import com.example.mall.R;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobUser;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //延时操作
        Timer timer = new Timer();
        timer.schedule(timetast, 2000);
    }


    TimerTask timetast = new TimerTask() {
        @Override
        public void run() {
            //    startActivity(new Intent(SplashActivity.this, MainActivity.class));
            //如果已登陆 跳转到 主页面  没有登陆 跳转的登陆页面
            BmobUser bmobUser = BmobUser.getCurrentUser(User.class);
            if (bmobUser != null) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                //没有登陆
                startActivity(new Intent(SplashActivity.this, LonginActivity.class));

            }

        }
    };

}
