package com.handsome.didi.Base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.handsome.didi.R;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.squareup.leakcanary.LeakCanary;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2016/5/main_bot_tab_home_off.
 */
public class BaseApplication extends Application {

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
        setupLeakCanary();
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
}
