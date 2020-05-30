package com.example.mall.Frament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mall.Adapter.HomeAdapter;
import com.example.mall.Bean.Post;
import com.example.mall.Bean.User;
import com.example.mall.R;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class FragmentHome extends Fragment {
    private RecyclerView rv;
    private SwipeRefreshLayout srlayout;
    private TextView HelloHome;
    private TextView username,ok;


    List<Post> data;


    private HomeAdapter homeAdapter;

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

        //user加载
        BmobUser bu=BmobUser.getCurrentUser(User.class);
        String userid=bu.getObjectId();
        BmobQuery<User> us=new BmobQuery<>();
        us.getObject(userid, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null){
                    username.setText(user.getUsername());
            }else {
                    username.setText("");
                    ok.setText("");
                }
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
                    homeAdapter=new HomeAdapter(getActivity(),data);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(homeAdapter);
                }else {
                    srlayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void initView() {
        rv = getActivity().findViewById(R.id.recyclerview);
        srlayout = getActivity().findViewById(R.id.swipe);
        HelloHome = getActivity().findViewById(R.id.HelloHome);
        username=getActivity().findViewById(R.id.user);
        ok=getActivity().findViewById(R.id.hy);


    }
}
