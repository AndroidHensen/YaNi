package com.handsome.didi.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.handsome.didi.Activity.Common.WebActivity;
import com.handsome.didi.Bean.Banner;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/1/3.
 */
public class MyBannerView extends RelativeLayout implements View.OnTouchListener, ViewPager.OnPageChangeListener {

    ViewPager targetVp;
    ArrayList<View> bannerList;
    ArrayList<View> indecationList;
    private Context context;
    int selectedBanner;
    private final static int BANNER_CHANGE = 0;
    //是否为网络图片加载
    boolean isNetImg = false;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BANNER_CHANGE:
                    targetVp.setCurrentItem(selectedBanner + 1);
                    mHandler.sendEmptyMessageDelayed(BANNER_CHANGE, 3000);
                    break;
            }
        }
    };

    public MyBannerView(Context context) {
        this(context, null);
    }

    public MyBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //初始化ViewPager
        targetVp = new ViewPager(context);
        targetVp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        targetVp.setOnTouchListener(this);
        targetVp.setOnPageChangeListener(this);
        addView(targetVp);
    }

    /**
     * 初始化轮播和指示器
     */
    public void initBannerForLocal(Activity activity, int[] bannerId) {
        //指示器布局
        LinearLayout ly_indecation = new LinearLayout(activity);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 15;
        params.rightMargin = 15;
        //指示器布局位置
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        addView(ly_indecation, params);
        //图片集合和指示器集合
        bannerList = new ArrayList<View>();
        indecationList = new ArrayList<View>();
        for (int i = 0; i < bannerId.length; i++) {
            //初始化图片
            ImageView iv = new ImageView(activity);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setBackgroundResource(bannerId[i]);
            bannerList.add(iv);
            //初始化指示器
            ImageView iv2 = new ImageView(activity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(8, 0, 0, 0);
            iv2.setLayoutParams(lp);
            if (i == 0) {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_on);
            } else {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_off);
            }
            indecationList.add(iv2);
            //添加到圆点布局
            ly_indecation.addView(iv2);
        }
        //初始化轮播数据
        HomeBannerAdapter bannerAdapter = new HomeBannerAdapter(bannerList, activity);
        targetVp.setAdapter(bannerAdapter);
        //初始化当前位置
        targetVp.setCurrentItem(bannerList.size() * 1000);
        //当前position
        selectedBanner = bannerList.size() * 1000;
    }

    /**
     * 初始化轮播和指示器
     */
    public void initBannerForNet(Activity activity, String[] bannerUrl) {
        //标识是网络加载
        isNetImg = true;
        //指示器布局
        LinearLayout ly_indecation = new LinearLayout(activity);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 15;
        params.rightMargin = 15;
        //指示器布局位置
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        addView(ly_indecation, params);
        //图片集合和指示器集合
        bannerList = new ArrayList<View>();
        indecationList = new ArrayList<View>();
        for (int i = 0; i < bannerUrl.length; i++) {
            //初始化图片
            ImageView iv = new ImageView(activity);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideUtils.setImageView(context, bannerUrl[i], iv);
            bannerList.add(iv);
            //初始化指示器
            ImageView iv2 = new ImageView(activity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(8, 0, 0, 0);
            iv2.setLayoutParams(lp);
            if (i == 0) {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_on);
            } else {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_off);
            }
            indecationList.add(iv2);
            //添加到圆点布局
            ly_indecation.addView(iv2);
        }
        //初始化轮播数据
        HomeBannerAdapter bannerAdapter = new HomeBannerAdapter(bannerList, activity);
        targetVp.setAdapter(bannerAdapter);
        //初始化当前位置
        targetVp.setCurrentItem(bannerList.size() * 1000);
        //当前position
        selectedBanner = bannerList.size() * 1000;
    }

    /**
     * 初始化轮播和指示器和点击事件
     */
    public void initBannerForNet(Activity activity, final List<Banner> bannerUrl) {
        //标识是网络加载
        isNetImg = true;
        //指示器布局
        LinearLayout ly_indecation = new LinearLayout(activity);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 15;
        params.rightMargin = 15;
        //指示器布局位置
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        addView(ly_indecation, params);
        //图片集合和指示器集合
        bannerList = new ArrayList<View>();
        indecationList = new ArrayList<View>();
        for (int i = 0; i < bannerUrl.size(); i++) {
            //初始化图片
            ImageView iv = new ImageView(activity);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideUtils.setImageView(context, bannerUrl.get(i).getImg_url(), iv);
            bannerList.add(iv);
            //初始化点击事件
            final int finalI = i;
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra("url", bannerUrl.get(finalI).getGo_url());
                    getContext().startActivity(intent);
                }
            });
            //初始化指示器
            ImageView iv2 = new ImageView(activity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(8, 0, 0, 0);
            iv2.setLayoutParams(lp);
            if (i == 0) {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_on);
            } else {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_off);
            }
            indecationList.add(iv2);
            //添加到圆点布局
            ly_indecation.addView(iv2);
        }
        //初始化轮播数据
        HomeBannerAdapter bannerAdapter = new HomeBannerAdapter(bannerList, activity);
        targetVp.setAdapter(bannerAdapter);
        //初始化当前位置
        targetVp.setCurrentItem(bannerList.size() * 1000);
        //当前position
        selectedBanner = bannerList.size() * 1000;
    }

    /**
     * 指示器高亮
     *
     * @param currentPoint
     */
    private void bannerPointLight(int currentPoint) {
        for (int i = 0; i < indecationList.size(); i++) {
            if (currentPoint == i) {
                indecationList.get(i).setBackgroundResource(R.drawable.home_top_ic_point_on);
            } else {
                indecationList.get(i).setBackgroundResource(R.drawable.home_top_ic_point_off);
            }
        }
    }


    /**
     * 开始轮播
     */
    public void startBanner() {
        //开启轮播
        mHandler.sendEmptyMessageDelayed(BANNER_CHANGE, 3000);
    }

    /**
     * 结束轮播
     */
    public void endBanner() {
        //结束轮播
        mHandler.removeCallbacksAndMessages(null);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //改变当前位置指针
        selectedBanner = position;
        //改变指示器变化
        bannerPointLight(position % indecationList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //如果是网络加载则没必要监听
        if (isNetImg) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //结束轮播
                mHandler.removeCallbacksAndMessages(null);
                break;
            case MotionEvent.ACTION_UP:
                //开启轮播
                mHandler.sendEmptyMessageDelayed(BANNER_CHANGE, 3000);
                break;
            case MotionEvent.ACTION_CANCEL:
                //开启轮播
                mHandler.sendEmptyMessageDelayed(BANNER_CHANGE, 3000);
                break;
        }
        return false;
    }

    /**
     * 适配器
     */
    public class HomeBannerAdapter extends PagerAdapter {

        private List<View> views;
        private Context context;

        public HomeBannerAdapter(List<View> views, Context context) {
            this.context = context;
            this.views = views;
        }

        public Object instantiateItem(View container, int position) {
            final int currentItem = position % views.size();
            ((ViewPager) container).addView(views.get(currentItem));
            return views.get(currentItem);
        }

        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        public int getCount() {
            return Integer.MAX_VALUE;
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }
    }
}
