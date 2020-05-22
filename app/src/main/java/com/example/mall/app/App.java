package com.example.mall.app;

import android.app.Application;
import android.util.Log;

import cn.bmob.v3.Bmob;

public class App extends Application {
    private String APP_KEY = "b7675d206c5aca1743cc7a7a1df3f078";

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, APP_KEY);
        Log.e("初始化成功", APP_KEY);
    }
}
