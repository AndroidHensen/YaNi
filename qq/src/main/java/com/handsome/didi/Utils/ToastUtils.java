package com.handsome.didi.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by handsome on 2016/3/28.
 */
public class ToastUtils {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
