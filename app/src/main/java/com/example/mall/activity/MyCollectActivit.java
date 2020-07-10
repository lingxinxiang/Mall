package com.example.mall.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mall.Frament.Fragment_Comunity;
import com.example.mall.Frament.Fragment_Push;
import com.example.mall.R;
import com.het.smarttab.v4.FragmentPagerItem;
import com.het.smarttab.v4.FragmentPagerItems;
import com.het.smarttab.v4.FragmentStatePagerItemAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class MyCollectActivit extends AppCompatActivity {
    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;
    private FragmentStatePagerItemAdapter fragadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollect);
        initView();

        viewPager.setOffscreenPageLimit(3);
        initTab();
    }

    private void initTab() {
        String[] tabs = new String[]{"帖子", "论坛"};
        FragmentPagerItems pages=new FragmentPagerItems(MyCollectActivit.this);
        for (int i = 0; i < tabs.length; i++) {
            Bundle args=new Bundle();
            args.putString("name",tabs[i]);
        }
        pages.add(FragmentPagerItem.of(tabs[0], Fragment_Push.class));
        pages.add(FragmentPagerItem.of(tabs[1], Fragment_Comunity.class));

        viewPager.removeAllViews();
        fragadapter=new FragmentStatePagerItemAdapter(getSupportFragmentManager(),pages);
        viewPager.setAdapter(fragadapter);
        smartTabLayout.setViewPager(viewPager);

        
    }

    private void initView() {
        smartTabLayout = findViewById(R.id.mycollecttab);
        viewPager = findViewById(R.id.mycollectvp);
    }
}
