package com.example.mall.Frament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mall.Bean.Post;
import com.example.mall.R;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FramentHome extends Fragment {
    private RecyclerView rv;
    private SwipeRefreshLayout srlayout;
    private TextView HelloHome;
    List<Post> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //逻辑处理
        initView();
        Bmob.initialize(getActivity(), "APP_KEY");
        //初始刷新
        Refresh();

        srlayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_red_light, android.R.color.holo_blue_light);
        srlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                Refresh();
            }
        });

    }

    private void Refresh() {
        BmobQuery<Post> Po = new BmobQuery<>();
        Po.order("-createdAt");
        Po.setLimit(1000);
        Po.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                srlayout.setRefreshing(false);
                if (e == null) {
                    data=list;


                }

            }
        });

    }


    private void initView() {
        rv = getActivity().findViewById(R.id.recyclerview);
        srlayout = getActivity().findViewById(R.id.swipe);
        HelloHome = getActivity().findViewById(R.id.HelloHome);


    }
}
