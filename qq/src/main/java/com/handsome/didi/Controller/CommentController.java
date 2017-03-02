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

    public CommentController(Context context) {
        super(context);
    }

    public interface OnQueryListener {
        void onQuery(List<Comment> list);
    }

    /**
     * 查询评论
     *
     * @param listener
     */
    public void query(String OID, final OnQueryListener listener) {
        try {
            BmobQuery<Comment> query = new BmobQuery<>();
            query.order("id");
            query.addWhereEqualTo("S_OID", OID);
            query.findObjects(new FindListener<Comment>() {
                @Override
                public void done(List<Comment> list, BmobException e) {
                    if (listener != null) {
                        listener.onQuery(list);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

}
