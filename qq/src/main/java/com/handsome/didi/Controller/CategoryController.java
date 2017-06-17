package com.handsome.didi.Controller;

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

    public static CategoryController categoryController;

    public static CategoryController getInstance() {
        if (categoryController == null) {
            synchronized (CategoryController.class) {
                if (categoryController == null) {
                    categoryController = new CategoryController();
                }
            }
        }
        return categoryController;
    }

    /**
     * 查询分类
     *
     * @param listener
     */
    public void query(final OnBmobListener listener) {
        BmobQuery<Category> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.order("id");
        query.findObjects(new FindListener<Category>() {
            @Override
            public void done(List<Category> list, BmobException e) {
                if (e != null) {
                    listener.onError("error Code:" + e.getErrorCode());
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
