package com.example.mall.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mall.Bean.Post;
import com.example.mall.Bean.User;
import com.example.mall.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PushContActivity extends AppCompatActivity {
    private EditText pushcontent;
    private ImageView back;
    private Button push;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushcont);
        initView();


        //发帖操作
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pushcontent.getText().toString().isEmpty()){
                    Toast.makeText(PushContActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                }else {
                    User user = BmobUser.getCurrentUser(User.class);
                    Post po = new Post();
                    po.setName(user.getUsername());
                    po.setContent(pushcontent.getText().toString());
                    po.setAuthor(user);
              /*      po.setObjectId("0");
                    po.setObjectId(user.getObjectId());*/
                    po.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                pushcontent.setText("");
                                Toast.makeText(PushContActivity.this, "发布成功", Toast.LENGTH_SHORT).show();

                                finish();
                            }else {
                                Toast.makeText(PushContActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        pushcontent = findViewById(R.id.pushcontent);
        push = findViewById(R.id.push);
        back = findViewById(R.id.back);
    }

}
