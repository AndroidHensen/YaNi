package com.handsome.didi.Utils;

import com.handsome.didi.Bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/9/20.
 */

public class ViewUtils {

    /**
     * EventBus更新UI
     */
    public static void onChangeDataInUI(String className) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.className = className;
        EventBus.getDefault().post(messageEvent);
    }

}
