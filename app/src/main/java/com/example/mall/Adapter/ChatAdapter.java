package com.example.mall.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mall.Bean.Comunity;
import com.example.mall.R;
import com.example.mall.activity.ReciveActivity;

import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

   private Context context;
   private List<Comunity>data;

    private final int N_TYPE = 0;
    private final int F_TYPE = 1;
    //加载15条消息
    private int Max_num = 15;
    private Boolean isfootview = true;//是否有footview


   public ChatAdapter(Context context,List<Comunity>data){
       this.context=context;
       this.data=data;

   }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comunity_item, parent, false);
        View footview = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item, parent, false);
        if (viewType == F_TYPE) {
            return new ChatAdapter.RecyclerViewHolder(footview, F_TYPE);
        } else {
            return new ChatAdapter.RecyclerViewHolder(view, N_TYPE);
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
            final ChatAdapter.RecyclerViewHolder recyclerViewHolder = (ChatAdapter.RecyclerViewHolder) holder;
            final Comunity comunity = data.get(position);

            recyclerViewHolder.c_name.setText(comunity.getName());
            recyclerViewHolder.c_info.setText(comunity.getInfo());
            recyclerViewHolder.c_user.setText(comunity.getUser().getUsername());

            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = recyclerViewHolder.getAdapterPosition();
                    Intent in = new Intent(context, ReciveActivity.class);
                    in.putExtra("c_name",comunity.getName());
                    in.putExtra("c_info",comunity.getInfo());
                    in.putExtra("c_user",comunity.getUser().getUsername());

                    in.putExtra("id",data.get(position).getObjectId());
                    context.startActivity(in);
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == Max_num - 1){
            return F_TYPE;  //底部type
        }else {
            return N_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if (data.size() < Max_num){
            return data.size();
        }
        return Max_num;
    }


    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView c_name,c_info,c_user; //ord_item的TextView
        public TextView Loading;

        public RecyclerViewHolder(View itemview, int view_type) {
            super(itemview);
            if (view_type == N_TYPE) {
                c_name = itemview.findViewById(R.id.c_name);
                c_info = itemview.findViewById(R.id.c_info);
                c_user = itemview.findViewById(R.id.c_user);
            } else if (view_type == F_TYPE) {
                Loading = itemview.findViewById(R.id.tv_footText);
            }

        }
    }
}
