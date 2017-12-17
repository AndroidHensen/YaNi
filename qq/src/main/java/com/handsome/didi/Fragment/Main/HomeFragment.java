package com.handsome.didi.Fragment.Main;

import android.Manifest;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handsome.banner.FastBanner;
import com.handsome.banner.adapter.BannerAdapter;
import com.handsome.didi.Activity.Home.CardActivity;
import com.handsome.didi.Activity.Home.SearchActivity;
import com.handsome.didi.Activity.Home.DeliveryActivity;
import com.handsome.didi.Activity.Home.LoveActivity;
import com.handsome.didi.Activity.Home.RechargeActivity;
import com.handsome.didi.Adapter.Home.ShopAdapter;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Banner;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.Sort;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.BannerController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.SortController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;
import com.handsome.didi.Utils.SpeechUtils;
import com.handsome.didi.View.MyGridView;
import com.handsome.didi.zxing.activity.CaptureActivity;
import com.handsome.menublock.FastMenuAdapter;
import com.handsome.menublock.FastMenuBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class HomeFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    private BannerController bannerController;
    private ShopController shopController;
    private SortController sortController;
    private ActivityController activityController;
    //整页
    private int currentPage;
    private PullToRefreshScrollView sv_main;
    private final static int REFRESH_CHANGE = 1;
    //首页轮播
    private FastBanner fb_banner;
    private List<Banner> bannerList;
    //商品展示
    private MyGridView gv_shops;
    private ShopAdapter shopAdapter;
    private List<Shop> shopList;
    //头部搜索框
    private ImageView iv_speech;
    private TextView tv_search;
    private LinearLayout ly_zxing;
    //中部菜单
    private FastMenuBlock fmb_center_menu;
    private int[] images = {R.drawable.home_mid_ic_menu_wdgz, R.drawable.home_mid_ic_menu_wlcx, R.drawable.home_mid_ic_menu_cz,
            R.drawable.home_mid_ic_menu_dyp, R.drawable.home_mid_ic_menu_yxcz, R.drawable.home_mid_ic_menu_lkq,
            R.drawable.home_mid_ic_menu_ljd, R.drawable.home_mid_ic_menu_gd};
    private String[] title = {"我的关注", "物流查询", "充值", "电影票", "游戏充值", "领卡券", "领金豆", "更多"};
    //特色好货、超实惠
    private int[] iv_tshh = {R.id.iv_tshh_01, R.id.iv_tshh_02, R.id.iv_tshh_03, R.id.iv_tshh_04,
            R.id.iv_tshh_05, R.id.iv_tshh_06, R.id.iv_tshh_07, R.id.iv_tshh_08, R.id.iv_csh_01,
            R.id.iv_csh_02, R.id.iv_csh_03, R.id.iv_csh_04, R.id.iv_csh_05, R.id.iv_csh_06,
            R.id.iv_csh_07, R.id.iv_csh_08};
    private List<Sort> sortList;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_CHANGE:
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
        fb_banner = findView(R.id.fb_banner);
        gv_shops = findView(R.id.gv_shops);
        sv_main = findView(R.id.sv_main);
        iv_speech = findView(R.id.iv_speech);
        tv_search = findView(R.id.tv_search);
        ly_zxing = findView(R.id.ly_zxing);
        fmb_center_menu = findView(R.id.fmb_center_menu);
    }

    @Override
    public void initData() {
        bannerController = BannerController.getInstance();
        sortController = SortController.getInstance();
        shopController = ShopController.getInstance();
        activityController = ActivityController.getInstance();

        //初始化中间菜单栏
        initCenterMenu();
        //初始化产品展示
        initShop();
        //初始化轮播图展示
        initBanner();
        //超实惠、特色好货
        initSort();
    }

    @Override
    public void initListener() {
        setOnClick(iv_speech);
        setOnClick(tv_search);
        setOnClick(ly_zxing);
        sv_main.setOnRefreshListener(this);
        gv_shops.setOnItemClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.iv_speech:
                //语音识别结果
                requestPermissions(Manifest.permission.RECORD_AUDIO);
                SpeechUtils.initSpeech(getActivity());
                break;
            case R.id.tv_search:
                //开启查询界面
                startActivity(SearchActivity.class);
                break;
            case R.id.ly_zxing:
                //开启扫描二维码
                requestPermissions(Manifest.permission.CAMERA);
                startActivity(CaptureActivity.class);
                break;
        }
    }

    /**
     * 初始化中间菜单栏
     */
    private void initCenterMenu() {
        fmb_center_menu.setAdapter(fastMenuAdapter);
    }

    /**
     * 初始化产品展示
     */
    private void initShop() {
        currentPage = 0;
        shopList = new ArrayList<>();
        shopController.query(currentPage, new ShopController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                shopList = (List<Shop>) list;
                shopAdapter = new ShopAdapter(getActivity(), shopList);
                gv_shops.setAdapter(shopAdapter);
                mHandler.sendEmptyMessageDelayed(REFRESH_CHANGE, 200);
            }

            @Override
            public void onError(String error) {
                showToast(error);
                mHandler.sendEmptyMessageDelayed(REFRESH_CHANGE, 200);
            }
        });
    }

    /**
     * 初始化轮播图展示
     */
    private void initBanner() {
        bannerController.query(new BannerController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                bannerList = (List<Banner>) list;
                fb_banner.setAdapter(fastBannerAdapter);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }

        });
    }

    /**
     * 超实惠、特色好货
     */
    private void initSort() {
        sortList = new ArrayList<>();
        sortController.query(new SortController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                sortList = (List<Sort>) list;
                for (int i = 0; i < sortList.size(); i++) {
                    //图片
                    GlideUtils.displayImage(getContext(), sortList.get(i).img_url, (ImageView) findView(iv_tshh[i]));
                    //点击事件
                    final int finalI = i;
                    findView(iv_tshh[i]).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activityController.startWebActivityWithUrl(getActivity(), sortList.get(finalI).go_url);
                        }
                    });
                }
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //商品点击事件
        activityController.startDetailActivityWithShop(getActivity(), shopList.get(position));
    }

    /**
     * 加载下一页
     */
    public void initShopNextPage() {
        currentPage++;
        shopController.query(currentPage, new ShopController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                shopList.addAll((List<Shop>) list);
                shopAdapter.notifyDataSetChanged();
                mHandler.sendEmptyMessageDelayed(REFRESH_CHANGE, 200);
            }

            @Override
            public void onError(String error) {
                showToast(error);
                mHandler.sendEmptyMessageDelayed(REFRESH_CHANGE, 200);
            }
        });
    }

    /**
     * 下拉刷新回调
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        initData();
    }

    /**
     * 上拉加载回调
     *
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        initShopNextPage();
    }

    /**
     *
     */
    private BannerAdapter fastBannerAdapter = new BannerAdapter() {
        @Override
        public View getView(int i) {
            String img_url = bannerList.get(i).img_url;
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideUtils.displayImage(getActivity(), img_url, imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return bannerList.size();
        }

        @Override
        public void onClick(int i) {
            String go_url = bannerList.get(i).go_url;
            activityController.startWebActivityWithUrl(getActivity(), go_url);
        }
    };

    /**
     *
     */
    private FastMenuAdapter fastMenuAdapter = new FastMenuAdapter() {
        @Override
        public Object getView(int i) {
            return images[i];
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public String getTitle(int i) {
            return title[i];
        }

        @Override
        public void onClick(int i) {
            switch (i) {
                case 0:
                    //开启我的关注
                    startActivity(LoveActivity.class);
                    break;
                case 1:
                    //开启物流查询页面
                    startActivity(DeliveryActivity.class);
                    break;
                case 2:
                case 4:
                    //开启充值页面
                    startActivity(RechargeActivity.class);
                    break;
                case 3:
                    //开启电影票页面
                    activityController.startWebActivityWithUrl(getActivity(), "http://m.wepiao.com/");
                    break;
                case 5:
                    //开启领金券页面
                    startActivity(CardActivity.class);
                    break;
                case 6:
                    //开启领京豆页面
                    showToast("功能未开启");
                    break;
                case 7:
                    showToast("没有更多了");
                    break;
            }
        }
    };
}
