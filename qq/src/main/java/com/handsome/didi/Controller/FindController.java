package com.handsome.didi.Controller;

import android.content.Context;

import com.handsome.didi.Bean.Banner;
import com.handsome.didi.Bean.Find;

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
public class FindController extends CommonController{

    public FindController(Context context) {
        super(context);
    }

    public interface OnQueryListener {
        void onQuery(List<Find> list);
    }

    /**
     * 查询发现
     *
     * @param listener
     */
    public void query(final OnQueryListener listener) {
        BmobQuery<Find> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.order("id");
        query.findObjects(new FindListener<Find>() {
            @Override
            public void done(List<Find> list, BmobException e) {
                if (listener != null) {
                    listener.onQuery(list);
                }
            }
        });
    }

}
