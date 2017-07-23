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
    public int pageCount = 12;
    //加载的数量限制
    public int limit_page = 50;
    //重连机制的总时间
    public int connect_time = 3000;
    //重连机制的间隔时间
    public int interval_time = 3000;

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


    /**
     * bmob文件接口
     */
    public interface onBmobInsertListener {
        void onSuccess(String success);

        void onError(String error);

        void onLoading(int loading);
    }
}
