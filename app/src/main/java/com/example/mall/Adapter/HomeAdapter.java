package com.example.mall.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mall.Bean.Post;
import com.example.mall.R;
import com.example.mall.activity.LonginActivity;
import com.example.mall.activity.ReciveActivity;

import java.util.List;

import cn.bmob.v3.BmobUser;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Post> data;

    private final int N_TYPE = 0;
    private final int F_TYPE = 1;
    //加载15条消息
    private int Max_num = 15;
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
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //自增加8
                    Max_num += 8;
                    notifyDataSetChanged();
                }
            }, 2000);
        } else {
            //这是ord_item的内容
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            Post post = data.get(position);
            recyclerViewHolder.username.setText(post.getName());
            recyclerViewHolder.nickname.setText(post.getNickname());
            recyclerViewHolder.content.setText(post.getContent());
            recyclerViewHolder.time.setText(post.getCreatedAt());

            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerViewHolder.getAdapterPosition();
                    if (BmobUser.getCurrentUser(BmobUser.class) != null) {
                        Intent in = new Intent(context, ReciveActivity.class);
                        in.putExtra("username",post.getName());
                        in.putExtra("content",post.getContent());
                        in.putExtra("time",post.getCreatedAt());

                        in.putExtra("id", data.get(position).getObjectId());
                        context.startActivity(in);
                    } else {
                        Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, LonginActivity.class));

                    }
                }
            });
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == Max_num - 1) {
            //底部type
            return F_TYPE;
        } else {
            return N_TYPE;
        }
    }

    @Override
    //得到全部数量
    public int getItemCount() {
        if (data.size()<Max_num){
            return data.size();
        }
        return Max_num;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView username,nickname, content, time;
        public TextView Loading;

        public RecyclerViewHolder(View itemview, int view_type) {
            super(itemview);
            if (view_type == N_TYPE) {
                username=itemview.findViewById(R.id.username);
                nickname = itemview.findViewById(R.id.nickname);
                content = itemview.findViewById(R.id.tv_content);
                time = itemview.findViewById(R.id.time);
            } else if (view_type == F_TYPE) {
                Loading = itemview.findViewById(R.id.tv_footText);
            }

        }
    }
}
