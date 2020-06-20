package com.example.mall.Frament;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.mall.Adapter.ChatAdapter;
import com.example.mall.Bean.Comunity;
import com.example.mall.R;
import com.example.mall.activity.PushComunityActivity;
import com.example.mall.activity.PushContActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FragmentChat extends Fragment {
    private RecyclerView rv;
    private SwipeRefreshLayout srlayout;
    private FloatingActionButton add,addcontent,addcomunity;
    private RelativeLayout rvlayout;

    List<Comunity>data;
    private ChatAdapter chatAdapter;
    private PopupWindow pop;

    private View view;
    //讨论页面
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         view = getLayoutInflater().inflate(R.layout.pop_item,null);
        initView();

        //初始刷新一次
        Refresh();
        srlayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_red_light, android.R.color.holo_blue_light);
        srlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();

            }
        });
        //对FloatButton监听
        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
             pop=new PopupWindow(view,250,700,true);
             pop.setOutsideTouchable(true);
             pop.setFocusable(true);
             pop.showAtLocation(rvlayout,Gravity.CENTER,530,350);

            }
        });
        addcontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PushContActivity.class));
            }
        });
        addcomunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PushComunityActivity.class));
            }
        });

    }

    private void Refresh() {
        BmobQuery<Comunity> com=new BmobQuery<>();
        com.setLimit(1000);
        com.order("-createdAt");
        com.findObjects(new FindListener<Comunity>() {
            @Override
            public void done(List<Comunity> list, BmobException e) {
                srlayout.setRefreshing(false);
                if (e==null){
                    data=list;
                    chatAdapter=new ChatAdapter(getActivity(),data);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(chatAdapter);
                }else {
                    srlayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {
        rv=getActivity().findViewById(R.id.rvview);
        srlayout=getActivity().findViewById(R.id.sw);
        add=getActivity().findViewById(R.id.add);
        rvlayout = getActivity().findViewById(R.id.rvlayout);
        addcontent =view.findViewById(R.id.addcontent);
        addcomunity =view.findViewById(R.id.addcomunity);

    }
  /*  @Override
    public void onResume() {
        super.onResume();
        Refresh();
    }*/
}
