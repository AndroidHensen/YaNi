package com.handsome.didi.Controller;

import android.content.Context;
import android.os.CountDownTimer;

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
    public void query(final String OID, final OnBmobListener listener) {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.order("createdAt");
        query.addWhereEqualTo("S_OID", OID);
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e != null) {
                    listener.onError("服务器异常，正在重连");
                    //重连机制
                    new CountDownTimer(connect_time, interval_time) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            query(OID, listener);
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                    return;
                }
                if (list.isEmpty()) {
                    listener.onError("空空如也");
                    return;
                }
                if (listener != null) {
                    listener.onSuccess(list);
                }
            }
        });
    }

}
