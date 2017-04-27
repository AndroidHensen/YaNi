package com.handsome.didi.Activity.Home;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handsome.didi.Activity.Common.ConfirmOrderActivity;
import com.handsome.didi.Activity.Common.StoreActivity;
import com.handsome.didi.Adapter.Cart.CartAdapter;
import com.handsome.didi.Adapter.Home.ServiceAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Comment;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Controller.CommentController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;
import com.handsome.didi.View.MyBannerView;

import java.util.Arrays;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class DetailActivity extends BaseActivity implements PopupWindow.OnDismissListener {

    private Intent intent;
    private CommentController commentController;
    private StoreController storeController;
    private UserController userController;
    private ShopController shopController;
    //详细信息展示
    private MyBannerView vp_detail;
    private TextView tv_detail_name, tv_detail_discount_price, tv_detail_price, tv_detail_sell_num, tv_detail_address, tv_postage;
    private LinearLayout ll_share;
    private ScrollView ly_main;
    //底部按钮
    private LinearLayout ly_love, ly_cart;
    private ImageView iv_love;
    private TextView tv_join_cart, tv_buy;
    //弹出框
    private View popView;
    private PopupWindow popupWindow;
    private CartAdapter adapter;
    private ListView lv;
    private static final int TYPE_CART = 0x01;
    private static final int TYPE_SERVICE = 0x02;
    //服务保障
    private GridView gv_service;
    private ServiceAdapter serviceAdapter;
    private List<String> service;
    private LinearLayout ly_service;
    //店铺信息
    private TextView tv_store, tv_store_sort;
    private TextView tv_name, tv_love_num, tv_all_shop, tv_delivery_grade, tv_shop_grade, tv_store_grade;
    private LinearLayout ly_rate;
    private ImageView iv_icon;
    private Store store;
    private static final int MSG_STORE = 0x03;
    private static final int MSG_COMMENT = 0x04;
    //评论区
    private TextView tv_user_name, tv_comment_content, tv_comment_num, tv_comment_date;
    private TextView tv_all_comment;
    private int comment_num = 0;
    //数据
    private Shop shop;
    private Comment comment;
    private String OID;
    private String S_OID;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_STORE:
                    setStoreViews(store);
                    break;
                case MSG_COMMENT:
                    setCommentViews(comment);
                    break;
            }
        }


    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initViews() {
        vp_detail = findView(R.id.vp_detail);
        tv_detail_name = findView(R.id.tv_detail_name);
        tv_detail_discount_price = findView(R.id.tv_detail_discount_price);
        tv_detail_price = findView(R.id.tv_detail_price);
        tv_store = findView(R.id.tv_store);
        tv_store_sort = findView(R.id.tv_store_sort);
        tv_detail_sell_num = findView(R.id.tv_detail_sell_num);
        tv_detail_address = findView(R.id.tv_detail_address);
        ll_share = findView(R.id.ll_share);
        ly_main = findView(R.id.ly_main);
        ly_love = findView(R.id.ly_love);
        ly_cart = findView(R.id.ly_cart);
        iv_love = findView(R.id.iv_love);
        tv_join_cart = findView(R.id.tv_join_cart);
        tv_buy = findView(R.id.tv_buy);
        tv_postage = findView(R.id.tv_postage);
        gv_service = findView(R.id.gv_service);
        ly_service = findView(R.id.ly_service);
        ly_rate = findView(R.id.ly_rate);
        tv_name = findView(R.id.tv_name);
        tv_love_num = findView(R.id.tv_love_num);
        tv_all_shop = findView(R.id.tv_all_shop);
        tv_delivery_grade = findView(R.id.tv_delivery_grade);
        tv_shop_grade = findView(R.id.tv_shop_grade);
        tv_store_grade = findView(R.id.tv_store_grade);
        iv_icon = findView(R.id.iv_icon);
        tv_user_name = findView(R.id.tv_user_name);
        tv_comment_content = findView(R.id.tv_comment_content);
        tv_comment_num = findView(R.id.tv_comment_num);
        tv_comment_date = findView(R.id.tv_comment_date);
        tv_all_comment = findView(R.id.tv_all_comment);
    }

    @Override
    public void initListener() {
        setOnClick(ll_share);
        setOnClick(ly_love);
        setOnClick(tv_store);
        setOnClick(tv_store_sort);
        setOnClick(tv_join_cart);
        setOnClick(tv_buy);
        setOnClick(ly_cart);
        setOnClick(ly_service);
        setOnClick(tv_all_comment);
    }

    @Override
    public void initData() {
        commentController = CommentController.getInstance();
        storeController = StoreController.getInstance();
        userController = UserController.getInstance();
        shopController = ShopController.getInstance();
        //初始化商品详情页面
        initDetailViews();
    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ll_share:
                initShare();
                break;
            case R.id.ly_love:
                addUserLove();
                break;
            case R.id.tv_join_cart:
                addUserCart();
                break;
            case R.id.tv_buy:
                startActivity(ConfirmOrderActivity.class);
                break;
            case R.id.ly_cart:
                initPopupWindow(TYPE_CART);
                break;
            case R.id.ly_service:
                initPopupWindow(TYPE_SERVICE);
                break;
            case R.id.tv_finish:
                popupWindow.dismiss();
                break;
            case R.id.tv_all_comment:
                //跳转到全部评论页面
                storeController.startCommentActivityWithShopId(this, OID);
                break;
            case R.id.tv_store:
            case R.id.tv_store_sort:
                //跳转到商店页面
                storeController.startStoreActivityWithStoreId(this, S_OID);
                break;

        }
    }

    /**
     * 加入购物车
     */
    private void addUserCart() {
        userController.addUserCart(OID, new BaseController.onBmobUserListener() {
            @Override
            public void onSuccess(String success) {
                showToast(success);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }

            @Override
            public void onLoading(String loading) {
                showToast(loading);
            }
        });
    }

    /**
     * 加入我的关注
     */
    private void addUserLove() {
        userController.addUserLove(OID, iv_love, new BaseController.onBmobUserListener() {
            @Override
            public void onSuccess(String success) {
                showToast(success);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }

            @Override
            public void onLoading(String loading) {
                showToast(loading);
            }
        });
    }

    /**
     * 初始化popupWindow
     */
    private void initPopupWindow(int type) {
        popView = getLayoutInflater().inflate(R.layout.view_popup_detail, null);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //为popWindow添加动画效果
        popupWindow.setAnimationStyle(R.style.style_popup);
        //点击弹出窗口
        popupWindow.showAtLocation(ly_main, Gravity.BOTTOM, 0, 0);
        //设置背景色
        setBackgroundAlpha(0.5f);
        //设置PopupWindow的View点击事件
        TextView tv_finish = (TextView) popView.findViewById(R.id.tv_finish);
        tv_finish.setOnClickListener(this);
        //设置消失监听
        popupWindow.setOnDismissListener(this);
        //控件
        lv = (ListView) popView.findViewById(R.id.lv_detail);
        //根据类型设置PopupView数据
        if (type == TYPE_CART) {
            List<String> cartOid = userController.getCartOid();
            if (cartOid.isEmpty()) {
                return;
            }
            shopController.queryCartOrLove(cartOid, new ShopController.OnBmobListener() {
                @Override
                public void onSuccess(List<?> list) {
                    adapter = new CartAdapter(DetailActivity.this, (List<Shop>) list);
                    adapter.setEdit(false);
                    lv.setAdapter(adapter);
                }

                @Override
                public void onError(String error) {
                    showToast(error);
                }

            });
        } else if (type == TYPE_SERVICE) {
            lv.setAdapter(serviceAdapter);
        }
    }

    /**
     * 设置屏幕背景透明效果
     */
    public void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onDismiss() {
        setBackgroundAlpha(1);
    }

    /**
     * 初始化商品详情页面
     */
    private void initDetailViews() {
        shop = getIntent().getParcelableExtra("shop");
        OID = shop.getObjectId();
        S_OID = shop.S_OID;
        //关注按钮
        userController.initUserLove(OID, iv_love);
        //基本信息
        vp_detail.initShowImageForNet(this, shop.show_urls);
        tv_detail_name.setText(shop.name);
        tv_detail_price.setText("￥" + shop.price);
        tv_detail_address.setText(shop.address);
        tv_detail_sell_num.setText("月售" + shop.sell_num + "笔");
        tv_detail_discount_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_detail_discount_price.setText("￥" + shop.price_discount);
        tv_postage.setText("快递：" + shop.postage);
        //服务保障
        service = Arrays.asList(shop.service.split(","));
        serviceAdapter = new ServiceAdapter(this, service);
        gv_service.setAdapter(serviceAdapter);
        //店铺信息
        initStoreViews(S_OID);
        //评价信息
        initCommentViews(OID);
    }

    /**
     * 初始化店铺信息
     *
     * @param S_OID
     */
    private void initStoreViews(String S_OID) {
        storeController.query(S_OID, new StoreController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                store = (Store) list.get(0);
                mHandler.sendEmptyMessage(MSG_STORE);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }

        });
    }

    /**
     * 初始化评论信息
     *
     * @param OID
     */
    private void initCommentViews(String OID) {
        commentController.query(OID, new CommentController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                comment = (Comment) list.get(0);
                comment_num = list.size();
                mHandler.sendEmptyMessage(MSG_COMMENT);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }

        });
    }

    /**
     * 设置店铺信息
     *
     * @param store
     */
    private void setStoreViews(Store store) {
        //店铺信息
        tv_name.setText(store.name);
        GlideUtils.displayImage(this, store.img_url, iv_icon);
        tv_all_shop.setText(store.all_shop + "");
        tv_love_num.setText(store.love_num + "");
        tv_delivery_grade.setText(store.delivery_grade + "");
        tv_shop_grade.setText(store.shop_grade + "");
        tv_store_grade.setText(store.store_grade + "");
        //店铺等级
        userController.setUserRate(this, store.rate, ly_rate);
    }


    /**
     * 设置评论信息
     *
     * @param comment
     */
    private void setCommentViews(Comment comment) {
        tv_user_name.setText(comment.username);
        tv_comment_date.setText(comment.getCreatedAt());
        tv_comment_content.setText(comment.content);
        tv_comment_num.setText("宝贝评价(" + comment_num + ")");
    }

    /**
     * 开启一鍵分享
     */
    private void initShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我爱京东");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        // oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("我爱京东");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }


}
