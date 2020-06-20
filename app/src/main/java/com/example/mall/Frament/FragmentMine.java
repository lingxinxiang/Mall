package com.example.mall.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mall.Bean.User;
import com.example.mall.R;
import com.example.mall.activity.MyCollectActivit;
import com.example.mall.activity.MyComunityActivit;
import com.example.mall.activity.MyInfoActivity;
import com.example.mall.activity.MyPushActivity;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class FragmentMine extends Fragment {
    private TextView username;
    private Button loginout;
    private LinearLayout myinfo;
    private LinearLayout mypush;
    private LinearLayout mycomunity;
    private LinearLayout mycollect;
    private LinearLayout setting;

    private LinearLayout followactivity;
    private LinearLayout focusactivity;

    private TextView myfocusnum;

    private TextView fansnum;

    private ImageView mine_gender;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //控件获取
        initView();

        //加载我的信息
        getMyinfo();

/*
        loginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();
                startActivity(new Intent( getActivity(),LonginActivity.class));
                getActivity().finish();
            }
        });*/

        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到我的信息页面
                startActivity(new Intent(getActivity(), MyInfoActivity.class));
            }
        });
        mypush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到我的帖子
                startActivity(new Intent(getActivity(), MyPushActivity.class));

            }
        });

        mycomunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到我的论坛
                startActivity(new Intent(getActivity(), MyComunityActivit.class));
            }
        });
        mycollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到我的收藏
                startActivity(new Intent(getActivity(), MyCollectActivit.class));
            }
        });



    }

    private void getMyinfo() {
        //加载个人信息
        BmobUser bu = BmobUser.getCurrentUser(BmobUser.class);
        //获取到id
        String id = bu.getObjectId();
        //查询 加载
        BmobQuery<User>bmobQuery=new BmobQuery<>();
        bmobQuery.getObject(id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null){
                    username.setText(user.getUsername());
                  /*  if (user.getGender().equals("man")){
                        mine_gender.setImageResource(R.drawable.man);
                    }else {
                        mine_gender.setImageResource(R.drawable.gril);
                    }*/

                }else {
                    Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        username = getActivity().findViewById(R.id.username);
     //   nickname = getActivity().findViewById(R.id.nickname);
       // loginout = getActivity().findViewById(R.id.loginout);
        myinfo = getActivity().findViewById(R.id.myinfo);
        mypush=getActivity().findViewById(R.id.mypush);

        mycomunity = getActivity().findViewById(R.id.mycomunity);
        mycollect = getActivity().findViewById(R.id.mycollect);
        mine_gender = getActivity().findViewById(R.id.mine_gender);
        fansnum = getActivity().findViewById(R.id.fansnum);
        setting = getActivity().findViewById(R.id.setting);
        myfocusnum = getActivity().findViewById(R.id.myfocusnum);
        followactivity = getActivity().findViewById(R.id.followactivity);
        focusactivity = getActivity().findViewById(R.id.focusactivity);



    }
}
