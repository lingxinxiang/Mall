package com.example.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mall.R;



public class ReciveActivity extends AppCompatActivity {
    private TextView useranme,content,time;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive);
        initView();
        initData();

        //监听返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initData() {
        //第二中域加载
        Intent a=getIntent();
        Intent b=getIntent();
        Intent c=getIntent();

        String useranmea =a.getStringExtra("username");
        String contenta =b.getStringExtra("content");
        String timea =c.getStringExtra("time");
        useranme.setText(useranmea);
        content.setText(contenta);
        time.setText(timea);


      //第二中加载
      /*  Intent in=getIntent();
        String id=in.getStringExtra("id");
        Post po=new Post();
        BmobQuery<Post> query=new BmobQuery<>();
        query.getObject(id, new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
                if (e==null){
                    useranme.setText(post.getName());
                    content.setText(post.getContent());
                    time.setText(post.getCreatedAt());
                }else {
                    Toast.makeText(ReciveActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


    }

    private void initView() {
        useranme=findViewById(R.id.username);
        content=findViewById(R.id.content);
        time=findViewById(R.id.time);
        back=findViewById(R.id.back);
    }
}
