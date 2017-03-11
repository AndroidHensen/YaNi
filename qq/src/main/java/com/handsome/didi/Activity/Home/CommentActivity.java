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
    //评论
    private String OID;
    private Comment comment;
    private ListView lv_comment;
    private List<Comment> commentList;
    private CommentAdapter commentAdapter;
    //用户
    private User user;
    private String U_OID;
    private List<User> userList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    public void initViews() {
        lv_comment = findView(R.id.lv_comment);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("全部评价");
        setTitleCanBack();
        commentController = new CommentController(this);
        userController = new UserController(this);
        //获取数据
        OID = getIntent().getStringExtra("OID");
        //初始化评论区
        commentList = new ArrayList<>();
        userList = new ArrayList<>();
        commentController.query(OID, new CommentController.OnQueryListener() {
            @Override
            public void onQuery(List<Comment> list) {
                commentList = list;
                for (int i = 0; i < list.size(); i++) {
                    comment = commentList.get(i);
                    U_OID = comment.getU_OID();
                    initUser(U_OID);
                }
            }

            private void initUser(String U_OID) {
                userController.query(U_OID, new UserController.OnQueryListener() {
                    @Override
                    public void onQuery(List<User> list) {
                        user = list.get(0);
                        userList.add(user);
                        if (userList.size() == commentList.size()) {
                            commentAdapter = new CommentAdapter(CommentActivity.this, commentList, userList);
                            lv_comment.setAdapter(commentAdapter);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void processClick(View v) {

    }
}
