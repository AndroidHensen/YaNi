package com.handsome.didi.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author 许英俊 2017/6/19
 */
public class AlertUtils {

    public static void showAlert(Context context, String message) {
        Dialog dlg = new AlertDialog.Builder(context)
                .setTitle("温馨提示")
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, null)
                .setMessage(message)
                .create();
        dlg.show();
    }

    public static void showAlert(Context context, String message,
                                 CharSequence positiveButtontxt, DialogInterface.OnClickListener positiveListener,
                                 CharSequence negativeButtontxt, DialogInterface.OnClickListener negativeListener) {
        Dialog dlg = new AlertDialog.Builder(context)
                .setTitle("温馨提示")
                .setCancelable(true)
                .setPositiveButton(positiveButtontxt, positiveListener)
                .setNegativeButton(negativeButtontxt, negativeListener)
                .setMessage(message)
                .setCancelable(false)
                .create();
        dlg.show();
    }

    public static void showAlert(Context context, String message,
                                 CharSequence positiveButtontxt, DialogInterface.OnClickListener positiveListener) {
        Dialog dlg = new AlertDialog.Builder(context)
                .setTitle("温馨提示")
                .setCancelable(true)
                .setPositiveButton(positiveButtontxt, positiveListener)
                .setMessage(message)
                .setCancelable(false)
                .create();
        dlg.show();
    }
}
