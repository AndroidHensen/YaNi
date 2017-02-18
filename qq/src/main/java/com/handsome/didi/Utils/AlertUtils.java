package com.handsome.didi.Utils;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/18.
 */
public class AlertUtils {

    public static SweetAlertDialog pDialog;

    public static void showContentAlert(Context context, String content) {
        pDialog = new SweetAlertDialog(context);
        pDialog.setTitleText("提醒")
                .setConfirmText("确定")
                .setConfirmClickListener(null)
                .setContentText(content)
                .setCancelable(true);
        pDialog.show();
    }

    public static void showLoadingAlert(Context context, String title) {
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(title)
                .setConfirmText("确定")
                .setConfirmClickListener(null)
                .setCancelable(true);
        pDialog.show();
    }

    public static void showSuccessAlert(Context context, String content) {
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitleText("操作成功")
                .setConfirmText("确定")
                .setConfirmClickListener(null)
                .setContentText(content)
                .setCancelable(true);
        pDialog.show();
    }

    public static void showErrorAlert(Context context, String content) {
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitleText("操作失败")
                .setConfirmText("确定")
                .setConfirmClickListener(null)
                .setContentText(content)
                .setCancelable(true);
        pDialog.show();
    }

    public static void changeSuccessAlert(String content) {
        if (pDialog.isShowing()) {
            pDialog.setTitleText("操作成功")
                    .setContentText(content)
                    .setConfirmText("确定")
                    .setConfirmClickListener(null)
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
        }
    }

    public static void changeErrorAlert(String content) {
        if (pDialog.isShowing()) {
            pDialog.setTitleText("操作失败")
                    .setContentText(content)
                    .setConfirmText("确定")
                    .setConfirmClickListener(null)
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
        }
    }
}
