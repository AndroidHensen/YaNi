package com.handsome.didi.Base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;

import com.handsome.didi.Bean.DaoMaster;
import com.handsome.didi.Bean.DaoSession;
import com.handsome.didi.R;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.squareup.leakcanary.LeakCanary;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;
import me.iwf.photopicker.PhotoPicker;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2016/5/main_bot_tab_home_off.
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
        //初始化语音识别
        SpeechUtility.createUtility(this, getString(R.string.speech_appid));
        //配置LeakCanary
//        setupLeakCanary();
        //配置本地数据库
        setupDatabase();
    }

    /**
     * 配置分包支持
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
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

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "address.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取Dao对象
     */
    public static DaoSession getDaoInstant() {
        return daoSession;
    }

}
