package com.handsome.didi.Activity.Home;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Adapter.Home.CommentAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Comment;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.CommentController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivity {

    CommentController commentController;
    UserController userController;

    private TextView tv_title;
    //评论
    private long S_ID;
    private Comment comment;
    private ListView lv_comment;
    private List<Comment> commentList;
    private CommentAdapter commentAdapter;
    //用户
    private User user;
    private long U_ID;
    private List<User> userList;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_comment);
        lv_comment = (ListView) findViewById(R.id.lv_comment);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        commentController = new CommentController();
        userController = new UserController();
        //标题
        tv_title.setText("评价");
        //获取数据
        S_ID = getIntent().getLongExtra("S_ID", 0);
        //初始化评论区
        commentList = new ArrayList<>();
        userList = new ArrayList<>();
        commentController.query(new CommentController.OnQueryListener() {
            @Override
            public void onQuery(List<Comment> list) {
                commentList = list;
                for (int i = 0; i < list.size(); i++) {
                    comment = commentList.get(i);
                    U_ID = comment.getU_ID();
                    initUser(U_ID);
                }
            }

            private synchronized void initUser( long U_ID) {
                userController.query(new UserController.OnQueryListener() {
                    @Override
                    public synchronized void onQuery(List<User> list) {
                        user = list.get(0);
                        userList.add(user);
                        if (userList.size() == commentList.size()) {
                            commentAdapter = new CommentAdapter(CommentActivity.this, commentList, userList);
                            lv_comment.setAdapter(commentAdapter);
                        }
                    }
                }, U_ID);
            }
        }, S_ID);
    }

    @Override
    public void processClick(View v) {

    }
}
