package com.handsome.didi.Activity.Common;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Comment;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.util.ArrayList;

public class PhotoViewActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private TextView tv_title, tv_name, tv_date, tv_comment;
    private Button bt_back;
    private int position = 0;

    private Comment comment;
    private ViewPager vp_photo;
    private PhotoViewAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo_view;
    }

    @Override
    public void initViews() {
        vp_photo = findView(R.id.vp_photo);
        tv_title = findView(R.id.tv_title);
        tv_name = findView(R.id.tv_name);
        tv_date = findView(R.id.tv_date);
        tv_comment = findView(R.id.tv_comment);
        bt_back = findView(R.id.bt_back);
    }

    @Override
    public void initListener() {
        setOnClick(bt_back);
        vp_photo.setOnPageChangeListener(this);
    }

    @Override
    public void initData() {

        initPhotoViews();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_back:
                finish();
                break;
        }
    }

    /**
     * 初始化PhotoView
     */
    private void initPhotoViews() {
        comment = getIntent().getParcelableExtra("comment");
        adapter = new PhotoViewAdapter(this, (ArrayList<String>) comment.img_urls);
        vp_photo.setAdapter(adapter);
        vp_photo.setCurrentItem(position);
        //内容
        tv_title.setText((position + 1) + "/" + comment.img_urls.size());
        tv_name.setText(comment.username);
        tv_date.setText(comment.getCreatedAt());
        tv_comment.setText(comment.content);
    }

    /**
     * 解决PhotoView滑动的Bug
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tv_title.setText((position + 1) + "/" + comment.img_urls.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 适配器
     */
    public class PhotoViewAdapter extends PagerAdapter {

        private Context mContext;
        private ArrayList<String> list;

        public PhotoViewAdapter(Context context, ArrayList<String> list) {
            this.mContext = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ImageView photoView = new PhotoView(mContext);
            GlideUtils.displayImage(mContext, list.get(position), photoView);
            ((ViewPager) container).addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }
    }
}
