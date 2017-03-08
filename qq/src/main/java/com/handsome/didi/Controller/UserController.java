package com.handsome.didi.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.User;
import com.handsome.didi.R;
import com.handsome.didi.Utils.SweetAlertUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class UserController extends BaseController {

    public UserController(Context context) {
        super(context);
    }

    public interface OnQueryListener {
        void onQuery(List<User> list);
    }

    public interface onCompleteListener {
        void onComplete();
    }

    /**
     * 根据U_ID查询用户
     *
     * @param listener
     * @param U_OID
     */
    public void query(String U_OID, final OnQueryListener listener) {
        try {
            BmobQuery<User> query = new BmobQuery<>();
            query.setCachePolicy(mPolicy);
            query.setLimit(1);
            query.addWhereEqualTo("objectId", U_OID);
            query.findObjects(new FindListener<User>() {
                @Override
                public void done(List<User> list, BmobException e) {
                    if (e != null) {
                        showToast(e.getMessage());
                        return;
                    }
                    if (listener != null) {
                        listener.onQuery(list);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            SweetAlertUtils.showErrorAlert(mContext, "账户或密码不能为空");
            return;
        }

        SweetAlertUtils.showLoadingAlert(mContext, "正在登录");

        BmobUser.loginByAccount(username, password, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (user != null) {
                    SweetAlertUtils.changeSuccessAlert("登录成功");
                } else {
                    SweetAlertUtils.changeErrorAlert("登录失败");
                }
            }
        });
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param password_again
     */
    public void register(String username, String password, String password_again) {
        if (!password_again.equals(password)) {
            SweetAlertUtils.showErrorAlert(mContext, "两次密码必须一致");
            return;
        }
        if (username.isEmpty() || password.isEmpty() || password_again.isEmpty()) {
            SweetAlertUtils.showErrorAlert(mContext, "账户或密码不能为空");
            return;
        }

        SweetAlertUtils.showLoadingAlert(mContext, "正在注册");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRate(1);
        user.setSex(true);
        user.setAge(16);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    SweetAlertUtils.changeSuccessAlert("注册成功");
                } else {
                    SweetAlertUtils.changeErrorAlert("注册失败");
                }
            }
        });
    }


    /**
     * 退出登录
     */
    public void loginOut() {
        BmobUser.logOut();
        SweetAlertUtils.showSuccessAlert(mContext, "退出登录成功");
    }


    /**
     * 添加购物车商品
     *
     * @param objectId
     */
    public void addUserCart(String objectId) {
        try {
            List<String> cartOid = getCartOid();
            if (cartOid.contains(objectId)) {
                showToast("已经加入购物车列表");
                return;
            }
            cartOid.add(objectId);

            User user = getCurrentUser();
            user.setCart_oid(cartOid);
            user.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        showToast("加入购物车成功");
                    } else {
                        showToast("加入购物车失败");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除选中的购物车商品
     *
     * @param objectIds
     */
    public void deleteUserCart(List<String> objectIds, final onCompleteListener listener) {
        try {
            if (objectIds.size() == 0) {
                return;
            }
            List<String> cartOid = getCartOid();
            for (String objectId : objectIds) {
                cartOid.remove(objectId);
            }

            User user = getCurrentUser();
            user.setCart_oid(cartOid);
            user.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        showToast("删除商品成功");
                        if (listener != null) {
                            listener.onComplete();
                        }
                    } else {
                        showToast("删除商品失败");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加我的关注商品
     *
     * @param objectId
     */
    public void addUserLove(String objectId, final ImageView iv) {
        try {
            List<String> loveOid = getLoveOid();
            if (loveOid.contains(objectId)) {
                showToast("已经加入关注列表");
                return;
            }
            loveOid.add(objectId);

            User user = getCurrentUser();
            user.setLove_oid(loveOid);
            user.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        iv.setBackgroundResource(R.drawable.detail_bot_ic_love_on);
                        showToast("关注成功");
                    } else {
                        iv.setBackgroundResource(R.drawable.detail_bot_ic_love_off);
                        showToast("关注失败");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化用户关注图标
     *
     * @param objectId
     * @param iv
     */
    public void initUserLove(String objectId, ImageView iv) {
        List<String> loveOid = getLoveOid();
        if (loveOid.contains(objectId)) {
            iv.setBackgroundResource(R.drawable.detail_bot_ic_love_on);
        } else {
            iv.setBackgroundResource(R.drawable.detail_bot_ic_love_off);
        }
    }

    /**
     * 删除选中的关注商品
     *
     * @param objectIds
     */
    public void deleteUserLove(List<String> objectIds, final onCompleteListener listener) {
        try {
            if (objectIds.size() == 0) {
                return;
            }
            List<String> loveOid = getLoveOid();
            for (String objectId : objectIds) {
                loveOid.remove(objectId);
            }

            User user = getCurrentUser();
            user.setLove_oid(loveOid);
            user.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        showToast("删除商品成功");
                        if (listener != null) {
                            listener.onComplete();
                        }
                    } else {
                        showToast("删除商品失败");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置用户等级
     *
     * @param rate
     * @param resView
     */
    public void setUserRate(int rate, LinearLayout resView) {
        resView.removeAllViews();
        int resId = -1;
        if (rate > 0 && rate <= 5) {
            resId = R.drawable.detail_mid_ic_rate_red;
        } else if (rate > 5 && rate <= 10) {
            rate -= 5;
            resId = R.drawable.detail_mid_ic_rate_blue;
        } else if (rate > 10 && rate <= 15) {
            rate -= 10;
            resId = R.drawable.detail_mid_ic_rate_cap;
        }
        for (int i = 0; i < rate; i++) {
            ImageView iv = new ImageView(mContext);
            iv.setImageResource(resId);
            resView.addView(iv);
        }
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public User getCurrentUser() {
        return BmobUser.getCurrentUser(User.class);
    }

    /**
     * 是否已经登录
     *
     * @return
     */
    public boolean isLogin() {
        return getCurrentUser() != null;
    }

    /**
     * 获取购物车objectId列表
     *
     * @return
     */
    public List<String> getCartOid() {
        try {
            return getCurrentUser().getCart_oid();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 获取我的关注objectId列表
     *
     * @return
     */
    public List<String> getLoveOid() {
        try {
            return getCurrentUser().getLove_oid();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}

