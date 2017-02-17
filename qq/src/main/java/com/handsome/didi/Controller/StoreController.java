package com.handsome.didi.Controller;

import com.handsome.didi.Bean.Sort;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Controller.CommonController;

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
public class StoreController extends CommonController {

    public interface OnQueryListener {
        void onQuery(List<Store> list);
    }

    /**
     * 查询商店
     */
    public void query(final OnQueryListener listener, long S_ID) {
        BmobQuery<Store> query = new BmobQuery<>();
        query.addWhereEqualTo("id", S_ID);
        query.findObjects(new FindListener<Store>() {
            @Override
            public void done(List<Store> list, BmobException e) {
                if (listener != null) {
                    listener.onQuery(list);
                }
            }
        });
    }
}
