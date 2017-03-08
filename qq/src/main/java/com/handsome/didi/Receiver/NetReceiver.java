package com.handsome.didi.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2016/12/29.
 */
public class NetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //网络广播接收者
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
            NetworkInfo netInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (activeInfo != null) {
                //网络可用
                if (activeInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    //判断移动数据
                    if (netInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        Toast.makeText(context, "您正在使用移动数据", Toast.LENGTH_SHORT).show();
                    }
                    //判斷Wifi數據
                    if (wifiInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        Toast.makeText(context, "您正在使用Wifi数据", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "请检查网络是否已联网", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
