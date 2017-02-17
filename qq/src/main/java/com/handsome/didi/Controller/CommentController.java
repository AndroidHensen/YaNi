package com.handsome.didi.Controller;

import com.handsome.didi.Bean.Banner;
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
public class CommentController extends CommonController{

    public interface OnQueryListener {
        void onQuery(List<Comment> list);
    }

    /**
     * 查询评论
     *
     * @param listener
     */
    public void query(final OnQueryListener listener, long C_ID) {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.addWhereEqualTo("S_ID", C_ID);
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (listener != null) {
                    listener.onQuery(list);
                }
            }
        });
    }

}
