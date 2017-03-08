package com.handsome.didi.Controller;

import android.content.Context;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Category;

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
public class CategoryController extends BaseController {

    public CategoryController(Context context) {
        super(context);
    }

    public interface OnQueryListener {
        void onQuery(List<Category> list);
    }

    /**
     * 查询分类
     *
     * @param listener
     */
    public void query(final OnQueryListener listener) {
        try {
            BmobQuery<Category> query = new BmobQuery<>();
            query.setCachePolicy(mPolicy);
            query.order("id");
            query.findObjects(new FindListener<Category>() {
                @Override
                public void done(List<Category> list, BmobException e) {
                    if (e != null) {
                        showToast(e.getMessage());
                        return;
                    }
                    if (listener != null) {
                        listener.onQuery(list);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
