package com.handsome.didi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handsome.didi.Activity.Common.WebActivity;
import com.handsome.didi.Activity.Home.RechargeActivity;
import com.handsome.didi.Activity.Home.DeliveryActivity;
import com.handsome.didi.Activity.LoveActivity;
import com.handsome.didi.Activity.Common.SearchActivity;
import com.handsome.didi.Adapter.Home.ShopAdapter;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Banner;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.Sort;
import com.handsome.didi.Controller.BannerController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.SortController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.PermissionUtils;
import com.handsome.didi.Utils.SpeechUtils;
import com.handsome.didi.Utils.ToastUtils;
import com.handsome.didi.View.MyBannerView;
import com.handsome.didi.View.MyGridView;
import com.handsome.didi.zxing.activity.CaptureActivity;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class HomeFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    BannerController bannerController;
    ShopController shopController;
    SortController sortController;

    private BitmapUtils bitmapUtils;
    private Intent intent;
    //整页
    private PullToRefreshScrollView sv_main;
    private final static int REFRESH_CHAGE = 1;
    //首页轮播
    private MyBannerView vp_banner;
    private List<Banner> bannerList;
    //商品展示
    private MyGridView gv_shops;
    private ShopAdapter shopAdapter;
    private List<Shop> shopList;
    //头部搜索框
    private ImageView iv_speech;
    private TextView tv_search;
    private ImageView iv_zxing;
    //中部菜单
    private LinearLayout ly_menu_love, ly_menu_cz, ly_menu_dyp, ly_menu_wlcx,
            ly_menu_yxcz, ly_menu_xjk, ly_menu_ljd, ly_menu_gd;
    //特色好货、超实惠
    private int[] iv_tshh = {R.id.iv_tshh_01, R.id.iv_tshh_02, R.id.iv_tshh_03, R.id.iv_tshh_04,
            R.id.iv_tshh_05, R.id.iv_tshh_06, R.id.iv_tshh_07, R.id.iv_tshh_08, R.id.iv_csh_01,
            R.id.iv_csh_02, R.id.iv_csh_03, R.id.iv_csh_04, R.id.iv_csh_05, R.id.iv_csh_06,
            R.id.iv_csh_07, R.id.iv_csh_08};
    private List<Sort> sortList;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_CHAGE:
                    sv_main.onRefreshComplete();
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        vp_banner = findView(R.id.vp_banner);
        gv_shops = findView(R.id.gv_shops);
        sv_main = findView(R.id.sv_main);
        iv_speech = findView(R.id.iv_speech);
        tv_search = findView(R.id.tv_search);
        iv_zxing = findView(R.id.iv_zxing);
        ly_menu_love = findView(R.id.ly_menu_love);
        ly_menu_cz = findView(R.id.ly_menu_cz);
        ly_menu_dyp = findView(R.id.ly_menu_dyp);
        ly_menu_wlcx = findView(R.id.ly_menu_wlcx);
        ly_menu_yxcz = findView(R.id.ly_menu_yxcz);
        ly_menu_xjk = findView(R.id.ly_menu_xjk);
        ly_menu_ljd = findView(R.id.ly_menu_ljd);
        ly_menu_gd = findView(R.id.ly_menu_gd);
        //初始化bitmapUtils
        bitmapUtils = new BitmapUtils(getActivity());
        //申请权限
        PermissionUtils.StartPermissionWithCameraAndAudio(getActivity(), 0);
        //初始化焦点
        gv_shops.setFocusable(false);
    }

    @Override
    public void initData() {
        bannerController = new BannerController(getActivity());
        shopController = new ShopController(getActivity());
        sortController = new SortController(getActivity());

        //初始化产品展示
        initShop();
        //初始化轮播图展示
        initBanner();
        //超实惠、特色好货
        initSort();
    }

    @Override
    public void initListener() {
        sv_main.setOnRefreshListener(this);
        gv_shops.setOnItemClickListener(this);
        iv_speech.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        iv_zxing.setOnClickListener(this);
        ly_menu_love.setOnClickListener(this);
        ly_menu_cz.setOnClickListener(this);
        ly_menu_dyp.setOnClickListener(this);
        ly_menu_wlcx.setOnClickListener(this);
        ly_menu_yxcz.setOnClickListener(this);
        ly_menu_xjk.setOnClickListener(this);
        ly_menu_ljd.setOnClickListener(this);
        ly_menu_gd.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.iv_speech:
                //语音识别结果
                SpeechUtils.initSpeech(getActivity());
                break;
            case R.id.tv_search:
                //开启查询界面
                Intent intent3 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent3);
                break;
            case R.id.iv_zxing:
                //开启扫描二维码
                Intent intent4 = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent4);
                break;
            case R.id.ly_menu_love:
                //开启我的关注
                Intent intent5 = new Intent(getActivity(), LoveActivity.class);
                startActivity(intent5);
                break;
            case R.id.ly_menu_cz:
                //开启充值页面
                Intent intent6 = new Intent(getActivity(), RechargeActivity.class);
                startActivity(intent6);
                break;
            case R.id.ly_menu_dyp:
                //开启电影票页面
                break;
            case R.id.ly_menu_wlcx:
                //开启物流查询页面
                Intent intent8 = new Intent(getActivity(), DeliveryActivity.class);
                startActivity(intent8);
                break;
            case R.id.ly_menu_gd:
                ToastUtils.showToast(getActivity(), "没有更多了");
                break;
            default:
        }
    }

    /**
     * 初始化产品展示
     */
    private void initShop() {
        shopList = new ArrayList<>();
        shopController.query(new ShopController.OnQueryListener() {
            @Override
            public void onQuery(List<Shop> list) {
                shopList = list;
                shopAdapter = new ShopAdapter(getActivity(), shopList);
                gv_shops.setAdapter(shopAdapter);
            }
        });
    }

    /**
     * 初始化轮播图展示
     */
    private void initBanner() {
        shopList = new ArrayList<>();
        bannerController.query(new BannerController.OnQueryListener() {
            @Override
            public void onQuery(List<Banner> list) {
                bannerList = list;
                vp_banner.initBannerForNet(getActivity(), bannerList);
            }
        });
    }

    /**
     * 超实惠、特色好货
     */
    private void initSort() {
        sortList = new ArrayList<>();
        sortController.query(new SortController.OnQueryListener() {
            @Override
            public void onQuery(List<Sort> list) {
                sortList = list;
                for (int i = 0; i < sortList.size(); i++) {
                    bitmapUtils.display(findView(iv_tshh[i]), sortList.get(i).getImg_url());
                    //点击事件
                    final int finalI = i;
                    findView(iv_tshh[i]).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent(getActivity(), WebActivity.class);
                            intent.putExtra("url", sortList.get(finalI).getGo_url());
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        shopController.startDetailActivityWithShop(getActivity(), shopList.get(position));
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //完成刷新
        mHandler.sendEmptyMessageDelayed(REFRESH_CHAGE, 3000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        //加载商品
        shopController.query(new ShopController.OnQueryListener() {
            @Override
            public void onQuery(List<Shop> list) {
                shopList = list;
            }
        });
        shopAdapter.notifyDataSetChanged();
        //完成刷新
        mHandler.sendEmptyMessageDelayed(REFRESH_CHAGE, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始轮播
        vp_banner.startBanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止轮播
        vp_banner.endBanner();
    }

}
