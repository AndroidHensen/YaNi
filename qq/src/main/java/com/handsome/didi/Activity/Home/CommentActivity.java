package com.handsome.didi.Activity.Home;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handsome.didi.Adapter.Home.CommentAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Comment;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.CommentController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivity{

    private CommentController commentController;
    //评论
    private String OID;
    private ListView lv_comment;
    private List<Comment> commentList;
    private CommentAdapter commentAdapter;

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

        commentController = CommentController.getInstance();

        initCommentViews();
    }

    @Override
    public void processClick(View v) {

    }

    /**
     * 初始化评论
     */
    private void initCommentViews() {
        //获取数据
        OID = getIntent().getStringExtra("OID");
        //初始化评论区
        commentList = new ArrayList<>();
        commentController.query(OID, new CommentController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                commentList = (List<Comment>) list;
                commentAdapter = new CommentAdapter(CommentActivity.this, commentList);
                lv_comment.setAdapter(commentAdapter);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }

        });
    }
}
