package com.example.mall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.mall.Adapter.SectionsPagerAdapter;
import com.example.mall.Frament.FramentChat;
import com.example.mall.Frament.FramentHome;
import com.example.mall.Frament.FramentMine;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnAdapterChangeListener {
    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;
    private List<Fragment>fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        bottomNavigationBar = findViewById(R.id.bottom);
        initView();



    }

    private void initView() {
        initViewPager();
        initBottomNavigationBar();

    }

    private void initBottomNavigationBar() {

        //配置底部导航栏
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED); //自适应大小
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.setBarBackgroundColor(R.color.white)
                           .setActiveColor(R.color.green_normal)
                           .setInActiveColor(R.color.black);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.homepage_fill,"首页").setInactiveIconResource(R.drawable.homepage))
                 .addItem(new BottomNavigationItem(R.drawable.mobilephone_fill,"论坛").setInactiveIconResource(R.drawable.mobilephone))
                 .addItem(new BottomNavigationItem(R.drawable.mine_fill,"我的").setInactiveIconResource(R.drawable.mine))
                 .setFirstSelectedPosition(0)
                 .initialise();


    }

    private void initViewPager() {
        //配置ViewPager
        viewPager.setOffscreenPageLimit(3);
        fragments=new ArrayList<Fragment>();
        fragments.add(new FramentHome());
        fragments.add(new FramentChat());
        fragments.add(new FramentMine());

        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnAdapterChangeListener(this);
        viewPager.setCurrentItem(0);



    }


    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {


    }
}










 /*BmobUser user = BmobUser.getCurrentUser(User.class);
        String id=user.getObjectId();
        BmobQuery<User>myuser=new BmobQuery<User>();
        myuser.getObject(id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null){
                    mTvUsername.setText(user.getUsername());
                    mTvNickname.setText(user.getNickname());
                }else {
                    Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                }

            }
        });*/