package com.handsome.didi.Controller;

import android.content.Context;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Sort;

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
public class SortController extends BaseController {

    public static SortController sortController;

    public SortController(Context context) {
        super(context);
    }

    public static SortController getInstance(Context context) {
        if (sortController == null) {
            synchronized (SortController.class) {
                if (sortController == null) {
                    sortController = new SortController(context);
                }
            }
        }
        return sortController;
    }
    public interface OnQueryListener {void onQuery(List<Sort> list);}

    /**
     * 查询超实惠、特色好货
     * @param listener
     */
    public void query(final OnQueryListener listener) {
            BmobQuery<Sort> query = new BmobQuery<>();
            query.setCachePolicy(mPolicy);
            query.order("id,sort_type");
            query.findObjects(new FindListener<Sort>() {
                @Override
                public void done(List<Sort> list, BmobException e) {
                    if (e != null) {
                        showToast("error code:"+e.getErrorCode());
                        return;
                    }
                    if (listener != null) {
                        listener.onQuery(list);
                    }
                }
            });
    }

}
