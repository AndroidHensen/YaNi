package com.handsome.didi.Utils;

import android.content.Context;
import android.content.Intent;

import com.handsome.didi.Activity.Common.SearchActivity;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

/**
 * Created by handsome on 2016/4/17.
 */
public class SpeechUtils {

    /**
     * 初始化语音识别
     */
    public static void initSpeech(final Context context) {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(context, null);
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                if (!isLast) {
                    //解析语音
                    String result = GsonUtils.parseVoice(recognizerResult.getResultString());
                    //开启搜索页面
                    Intent intent = new Intent(context, SearchActivity.class);
                    intent.putExtra("result", result);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();
    }
}
