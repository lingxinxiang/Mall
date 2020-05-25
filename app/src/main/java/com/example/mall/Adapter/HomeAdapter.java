package com.example.mall.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mall.Bean.Post;
import com.example.mall.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Post> data;

    private final int N_TYPE = 0;
    private final int F_TYPE = 1;
    private int Max_nym = 15;
    private Boolean isfootview = true;//是否有footview


    public HomeAdapter(Context context, List<Post> data) {
        this.context = context;
        this.data = data;
    }

    ;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ord_item, parent, false);
        View footview = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item, parent, false);
        if (viewType == F_TYPE) {
            return new RecyclerViewHolder(footview, F_TYPE);
        } else {
            return new RecyclerViewHolder(view, N_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isfootview && (getItemViewType(position)) == F_TYPE) {
            //加载底部提示
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.Loading.setText("加载中...");
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Max_nym+=8;
                    notifyDataSetChanged();
                }
            },2000);
        }else {
            //这是ord_item的内容
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            Post post=data.get(position);
            recyclerViewHolder.nickname.setText(post.getNickname());
            recyclerViewHolder.content.setText(post.getContent());
            recyclerViewHolder.time.setText(post.getCreatedAt());
        }


    }



    @Override
    public int getItemCount() {
        return 0;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView nickname, content, time;
        public TextView Loading;

        public RecyclerViewHolder(View itemview, int view_type) {
            super(itemview);
            if (view_type==N_TYPE){
                nickname=itemview.findViewById(R.id.nickname);
                content=itemview.findViewById(R.id.tv_content);
                time=itemview.findViewById(R.id.time);
            }else if (view_type==F_TYPE){
                Loading=itemview.findViewById(R.id.tv_footText);
            }

        }
    }
}
