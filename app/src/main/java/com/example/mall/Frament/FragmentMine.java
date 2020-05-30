package com.example.mall.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mall.Bean.User;
import com.example.mall.R;
import com.example.mall.activity.LonginActivity;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class FragmentMine extends Fragment {
    private TextView username, nickname;
    private Button loginout;

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
        loginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();
                startActivity(new Intent( getActivity(),LonginActivity.class));
                getActivity().finish();
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
                    nickname.setText(user.getNickname());
                }else {
                    Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        username = getActivity().findViewById(R.id.username);
        nickname = getActivity().findViewById(R.id.nickname);
        loginout = getActivity().findViewById(R.id.loginout);


    }
}
