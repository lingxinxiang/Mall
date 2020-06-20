package com.example.mall.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mall.Bean.User;
import com.example.mall.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class MyInfoActivity extends AppCompatActivity {
    private ImageView back;
    private TextView my_name,my_pushnum,my_comnum,my_nickname,usercreattime;
    private TextView info_title;

    private ImageView my_gender;

    private String user_onlyid;
    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;
    private FragmentStatePagerAdapter fragadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //获取信息
        getInfo();
    }

    private void getInfo() {
        User user= BmobUser.getCurrentUser(User.class);
        String Id=user.getObjectId();
        BmobQuery<User>bmobQuery=new BmobQuery<>();
        bmobQuery.getObject(Id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null){
                    my_name.setText(user.getUsername());
                }else {
                    Toast.makeText(MyInfoActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void initView() {
        back = findViewById(R.id.back);
        my_name = findViewById(R.id.my_name);
//        my_pushnum = findViewById(R.id.my_pushnum);
//        my_comnum = findViewById(R.id.my_comnum);
        my_nickname = findViewById(R.id.mynickname);
//        usercreattime = findViewById(R.id.usercreattime);
        my_gender = findViewById(R.id.my_gender);
        info_title = findViewById(R.id.info_title);
     //   focus = findViewById(R.id.focus);
      //  editmyinfo = findViewById(R.id.editmyinfo);
       // smartTabLayout = findViewById(R.id.myinfotab);
        viewPager = findViewById(R.id.myinfovp);

       // focus_or_not = findViewById(R.id.focus);
    }
}
