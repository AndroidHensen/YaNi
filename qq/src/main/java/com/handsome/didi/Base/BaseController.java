package com.handsome.didi.Base;

import java.util.List;

import cn.bmob.v3.BmobQuery;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/16.
 */
public class BaseController {
    //缓存策略
    public BmobQuery.CachePolicy mPolicy = BmobQuery.CachePolicy.NETWORK_ONLY;
    //一页加载的数量
    public int pageCount = 16;

    public interface OnBmobListener {
        void onSuccess(List<?> list);

        void onError(String error);
    }

    public interface onBmobUserListener {
        void onSuccess(String success);

        void onError(String error);

        void onLoading(String loading);
    }
}
