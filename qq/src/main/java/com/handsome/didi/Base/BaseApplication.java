package com.handsome.didi.Base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.handsome.didi.Bean.DaoMaster;
import com.handsome.didi.Bean.DaoSession;
import com.handsome.didi.R;
import com.squareup.leakcanary.LeakCanary;
import com.thinkland.sdk.android.JuheSDKInitializer;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2016/5/11.
 */
public class BaseApplication extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化shareSDK
        ShareSDK.initSDK(this);
        //初始化Bmob
        Bmob.initialize(this, getString(R.string.bmob_appid));
        //初始化聚合数据
        JuheSDKInitializer.initialize(this);
        //配置数据库
        setupDatabase();
        //配置LeakCanary
        setupLeakCanary();
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     * 配置LeakCanary
     */
    private void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
