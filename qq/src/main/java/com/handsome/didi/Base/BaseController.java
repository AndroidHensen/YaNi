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
    public int pageCount = 8;
    //加载的数量
    public int limit_page = 20;

    /**
     * bmob查询接口
     */
    public interface OnBmobListener {
        void onSuccess(List<?> list);

        void onError(String error);
    }

    /**
     * bmob增删改接口
     */
    public interface OnBmobCommonListener {
        void onSuccess(String success);

        void onError(String error);
    }

    /**
     * bmob用户接口
     */
    public interface onBmobUserListener {
        void onSuccess(String success);

        void onError(String error);

        void onLoading(String loading);
    }
}
