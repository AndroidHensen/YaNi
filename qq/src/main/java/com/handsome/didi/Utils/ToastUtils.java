package com.handsome.didi.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.handsome.didi.R;

/**
 * @author 许英俊 2017/6/21
 */
public class ToastUtils {

    /**
     * 弹出自定义Toast
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        Toast toast = new Toast(context);
        View view = View.inflate(context, R.layout.view_toast, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_toast);
        textView.setText(msg);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setMargin(0, 0);
        toast.show();
    }
}
