package com.handsome.didi.Activity.Order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Controller.FindController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ArrayUtils;
import com.handsome.didi.Utils.DensityUtils;
import com.handsome.didi.Utils.GlideUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;

public class EvaluateActivity extends BaseActivity {

    private FindController findController;

    private Button bt_send;
    private RelativeLayout ly_add_img;
    private LinearLayout ly_pic;
    private TextView tv_good_goods;

    private ArrayList<String> photos;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                createImageView(photos, ly_pic);
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_evaluate;
    }

    @Override
    public void initViews() {
        ly_add_img = findView(R.id.ly_add_img);
        ly_pic = findView(R.id.ly_pic);
        bt_send = findView(R.id.bt_send);
        tv_good_goods = findView(R.id.tv_good_goods);
    }

    @Override
    public void initListener() {
        setOnClick(ly_add_img);
        setOnClick(bt_send);
    }

    @Override
    public void initData() {
        setTitleCanBack();
        setTitle("评价晒单");

        findController = FindController.getInstance();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_add_img:
                startPhotoPicker();
                break;
            case R.id.bt_send:
                //TODO:测试上传文件成功与否，会通过Log打印出来

                break;
            case R.id.tv_good_goods:
                tv_good_goods.setBackgroundResource(R.drawable.common_bg_orange_8x4);
                tv_good_goods.setTextColor(Color.WHITE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                mHandler.sendEmptyMessage(0);
            }
        }
    }

    /**
     * 打开图片选择器
     */
    private void startPhotoPicker() {
        PhotoPicker.builder()
                .setPhotoCount(3)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    /**
     * 动态创建插图
     *
     * @param image_urls
     * @param ly
     */
    private void createImageView(List<String> image_urls, LinearLayout ly) {
        //清空
        ly.removeAllViews();
        ViewGroup.LayoutParams params;
        params = new ViewGroup.LayoutParams(DensityUtils.dip2px(this, 100), DensityUtils.dip2px(this, 100));
        //动态添加
        for (int i = 0; i < image_urls.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setPadding(0, 0, 10, 0);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideUtils.displayImage(this, image_urls.get(i), imageView);
            ly.addView(imageView);
        }
    }
}
