package com.handsome.didi.Activity.Home;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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

import com.handsome.didi.Activity.Common.StoreActivity;
import com.handsome.didi.Adapter.Cart.CartAdapter;
import com.handsome.didi.Adapter.Home.ServiceAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Comment;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.CommentController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.didi.View.MyBannerView;
import com.lidroid.xutils.BitmapUtils;

import java.util.Arrays;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class DetailActivity extends BaseActivity implements PopupWindow.OnDismissListener {

    CommentController commentController;
    StoreController storeController;
    UserController userController;
    ShopController shopController;
    //展示
    private MyBannerView vp_detail;
    private TextView tv_detail_name, tv_detail_discount_price, tv_detail_price, tv_detail_sell_num, tv_detail_address, tv_postage;
    private LinearLayout ll_share;
    private ScrollView ly_main;
    //底部
    private LinearLayout ly_love, ly_cart;
    private ImageView iv_love;
    private TextView tv_join_cart, tv_buy;
    private boolean isLove;
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
    private TextView tv_store;
    private TextView tv_name, tv_love_num, tv_all_shop, tv_delivery_grade, tv_shop_grade, tv_store_grade;
    private LinearLayout ly_rate;
    private ImageView iv_icon;
    private Store store;
    private BitmapUtils bitmapUtils;
    private static final int MSG_STORE = 0x03;
    private static final int MSG_COMMENT = 0x04;
    private static final int MSG_USER = 0x05;
    //评论区
    private TextView tv_user_name, tv_comment_content, tv_comment_num, tv_comment_date;
    private TextView tv_all_comment;
    private int comment_num = 0;
    private LinearLayout ly_user_rate;
    //数据
    private Shop shop;
    private Comment comment;
    private User user;
    private String OID;
    private String S_OID;
    private Intent intent;

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
                case MSG_USER:
                    setUserViews(user);
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
        ly_user_rate = findView(R.id.ly_user_rate);
        bitmapUtils = new BitmapUtils(this);
    }

    @Override
    public void initListener() {
        ll_share.setOnClickListener(this);
        ly_love.setOnClickListener(this);
        tv_store.setOnClickListener(this);
        tv_join_cart.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        ly_cart.setOnClickListener(this);
        ly_service.setOnClickListener(this);
        tv_all_comment.setOnClickListener(this);
    }

    @Override
    public void initData() {
        commentController = new CommentController(this);
        storeController = new StoreController(this);
        userController = new UserController(this);
        shopController = new ShopController(this);
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
                break;
            case R.id.tv_join_cart:
                userController.addUserCart(OID);
                break;
            case R.id.tv_buy:
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
                intent = new Intent(this, CommentActivity.class);
                intent.putExtra("OID", OID);
                startActivity(intent);
                break;
            case R.id.tv_store:
                intent = new Intent(this, StoreActivity.class);
                intent.putExtra("S_OID", S_OID);
                startActivity(intent);
                break;
        }
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
        setOnPopupViewClick(popView);
        //设置消失监听
        popupWindow.setOnDismissListener(this);
        //控件
        lv = (ListView) popView.findViewById(R.id.lv_detail);
        //根据类型设置PopupView数据
        setOnPopupViewData(type);
    }

    /**
     * 设置PopupWindow的View点击事件
     *
     * @param popView
     */
    private void setOnPopupViewClick(View popView) {
        TextView tv_finish = (TextView) popView.findViewById(R.id.tv_finish);
        tv_finish.setOnClickListener(this);
    }

    /**
     * 根据类型设置PopupView数据
     *
     * @param type
     */
    private void setOnPopupViewData(int type) {
        if (type == TYPE_CART) {
            List<String> cartOid = userController.getCartOid();
            shopController.queryCartOrLove(cartOid, new ShopController.OnQueryListener() {
                @Override
                public void onQuery(List<Shop> list) {
                    adapter = new CartAdapter(DetailActivity.this, list);
                    lv.setAdapter(adapter);
                }
            });
        } else if (type == TYPE_SERVICE) {
            lv.setAdapter(serviceAdapter);
        }
    }


    /**
     * 初始化商品详情页面
     */
    private void initDetailViews() {
        shop = getIntent().getParcelableExtra("shop");
        OID = shop.getObjectId();
        S_OID = shop.getS_OID();
        //基本信息
        vp_detail.initBannerForNet(this, new String[]{shop.getUrl1(), shop.getUrl2(), shop.getUrl3(), shop.getUrl4()});
        tv_detail_name.setText(shop.getName());
        tv_detail_price.setText(shop.getPrice());
        tv_detail_address.setText(shop.getAddress());
        tv_detail_sell_num.setText("月售" + shop.getSell_num() + "笔");
        tv_detail_discount_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_detail_discount_price.setText(shop.getPrice_discount());
        tv_postage.setText("快递:" + shop.getPostage());
        //服务保障
        service = Arrays.asList(shop.getService().split(","));
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
        storeController.query(S_OID, new StoreController.OnQueryListener() {
            @Override
            public void onQuery(List<Store> list) {
                if (list.size() > 0) {
                    store = list.get(0);
                    mHandler.sendEmptyMessage(MSG_STORE);
                }
            }
        });
    }

    /**
     * 初始化评论信息
     *
     * @param OID
     */
    private void initCommentViews(String OID) {
        commentController.query(OID, new CommentController.OnQueryListener() {
            @Override
            public void onQuery(List<Comment> list) {
                comment = list.get(0);
                comment_num = list.size();
                mHandler.sendEmptyMessage(MSG_COMMENT);
                //查询用户信息
                initUser(comment.getU_OID());
                Log.e("TAG",comment.getU_OID());
            }

            private void initUser(String U_OID) {
                userController.query(U_OID, new UserController.OnQueryListener() {
                    @Override
                    public void onQuery(List<User> list) {
                        user = list.get(0);
                        Log.e("TAG",user.getObjectId());
                        mHandler.sendEmptyMessage(MSG_USER);
                    }
                });
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
        tv_name.setText(store.getName());
        bitmapUtils.display(iv_icon, store.getImg_url());
        tv_all_shop.setText(store.getAll_shop() + "");
        tv_love_num.setText(store.getLove_num() + "");
        tv_delivery_grade.setText(store.getDelivery_grade() + "");
        tv_shop_grade.setText(store.getShop_grade() + "");
        tv_store_grade.setText(store.getStore_grade() + "");
        //等级
        userController.setUserRate(store.getRate(), ly_rate);
    }


    /**
     * 设置评论信息
     *
     * @param comment
     */
    private void setCommentViews(Comment comment) {
        tv_comment_date.setText(comment.getDate());
        tv_comment_content.setText(comment.getContent());
        tv_comment_num.setText("宝贝评价(" + comment_num + ")");
    }


    /**
     * 设置用户信息
     *
     * @param user
     */
    private void setUserViews(User user) {
        tv_user_name.setText(user.getUsername());
        userController.setUserRate(user.getRate(), ly_user_rate);
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

}
