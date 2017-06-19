package com.handsome.didi.Controller;

import android.content.Context;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Comment;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class CommentController extends BaseController {

    public static CommentController commentController;

    public static CommentController getInstance() {
        if (commentController == null) {
            synchronized (CommentController.class) {
                if (commentController == null) {
                    commentController = new CommentController();
                }
            }
        }
        return commentController;
    }

    /**
     * 查询评论
     *
     * @param listener
     */
    public void query(String OID, final OnBmobListener listener) {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.order("createdAt");
        query.addWhereEqualTo("S_OID", OID);
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e != null) {
                    listener.onError("Server Error");
                    return;
                }
                if (list.isEmpty()) {
                    listener.onError("list is empty");
                    return;
                }
                if (listener != null) {
                    listener.onSuccess(list);
                }
            }
        });
    }

}
