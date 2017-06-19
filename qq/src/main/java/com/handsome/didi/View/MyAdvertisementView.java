package com.handsome.didi.View;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.handsome.didi.R;

/**
 * @author 许英俊 2017/6/19
 */
public class MyAdvertisementView extends Dialog implements View.OnClickListener {

    public MyAdvertisementView(Context context) {
        super(context);
        setContentView(R.layout.view_dialog_advertisement);
        setCanceledOnTouchOutside(true);
    }

    public void showDialog() {
        Window window = getWindow();
        window.setWindowAnimations(R.style.style_dialog);
        window.setBackgroundDrawableResource(R.color.colorTransparent);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.gravity = Gravity.CENTER;
        window.setAttributes(wl);
        show();
        findViewById(R.id.iv_advertisement).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
