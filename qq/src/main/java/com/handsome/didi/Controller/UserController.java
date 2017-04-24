package com.handsome.didi.Controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.User;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
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

    public static UserController userController;

    public static UserController getInstance() {
        if (userController == null) {
            synchronized (UserController.class) {
                if (userController == null) {
                    userController = new UserController();
                }
            }
        }
        return userController;
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @param listener
     */
    public void login(String username, String password, final onBmobUserListener listener) {
        if (username.isEmpty() || password.isEmpty()) {
            listener.onError("账户或密码不能为空");
            return;
        }
        listener.onLoading("正在登录");
        BmobUser.loginByAccount(username, password, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (user != null) {
                    listener.onSuccess("登录成功");
                } else {
                    listener.onError("登录失败");
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
     * @param listener
     */
    public void register(String username, String password, String password_again, final onBmobUserListener listener) {
        if (!password_again.equals(password)) {
            listener.onError("两次密码必须一致");
            return;
        }
        if (username.isEmpty() || password.isEmpty() || password_again.isEmpty()) {
            listener.onError("账户或密码不能为空");
            return;
        }
        listener.onLoading("正在注册");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.rate = 1;
        user.sex = true;
        user.age = 16;
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    listener.onSuccess("注册成功");
                } else {
                    listener.onError("注册失败");
                }
            }
        });
    }


    /**
     * 退出登录
     */
    public void loginOut(onBmobUserListener listener) {
        BmobUser.logOut();
        listener.onSuccess("退出登录成功");
    }


    /**
     * 添加购物车商品
     *
     * @param objectId
     */
    public void addUserCart(String objectId, final onBmobUserListener listener) {
        if (!isLogin()) {
            listener.onError("请登录后再加入我的购物车");
            return;
        }

        List<String> cartOid = getCartOid();
        if (cartOid.contains(objectId)) {
            listener.onError("已经加入购物车列表");
            return;
        }
        cartOid.add(objectId);

        User user = getCurrentUser();
        user.cart_oid = cartOid;
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    listener.onSuccess("加入购物车成功");
                } else {
                    listener.onError("加入购物车失败");
                }
            }
        });
    }

    /**
     * 删除选中的购物车商品
     *
     * @param objectIds
     */
    public void deleteUserCart(List<String> objectIds, final onBmobUserListener listener) {
        if (objectIds.size() == 0) {
            return;
        }
        List<String> cartOid = getCartOid();
        for (String objectId : objectIds) {
            cartOid.remove(objectId);
        }

        User user = getCurrentUser();
        user.cart_oid = cartOid;
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    if (listener != null) {
                        listener.onSuccess("删除商品成功");
                    }
                } else {
                    listener.onError("删除商品失败");
                }
            }
        });
    }

    /**
     * 添加我的关注商品
     *
     * @param objectId
     */
    public void addUserLove(String objectId, final ImageView iv, final onBmobUserListener listener) {
        if (!isLogin()) {
            listener.onError("请登录后再加入我的关注");
            return;
        }

        List<String> loveOid = getLoveOid();
        if (loveOid.contains(objectId)) {
            listener.onSuccess("已经加入关注列表");
            return;
        }
        loveOid.add(objectId);

        User user = getCurrentUser();
        user.love_oid = loveOid;
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    iv.setBackgroundResource(R.drawable.detail_bot_ic_love_on);
                    listener.onSuccess("关注成功");
                } else {
                    iv.setBackgroundResource(R.drawable.detail_bot_ic_love_off);
                    listener.onError("关注失败");
                }
            }
        });
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
    public void deleteUserLove(List<String> objectIds, final onBmobUserListener listener) {
        if (objectIds.size() == 0) {
            return;
        }
        List<String> loveOid = getLoveOid();
        for (String objectId : objectIds) {
            loveOid.remove(objectId);
        }

        User user = getCurrentUser();
        user.love_oid = loveOid;
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    if (listener != null) {
                        listener.onSuccess("删除商品成功");
                    }
                } else {
                    listener.onError("删除商品失败");
                }
            }
        });
    }


    /**
     * 设置用户等级
     *
     * @param rate
     * @param resView
     * @param context
     */
    public void setUserRate(Context context, int rate, LinearLayout resView) {
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
            ImageView iv = new ImageView(context);
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
        User user = BmobUser.getCurrentUser(User.class);
        if (user == null) {
            return null;
        } else {
            return user;
        }
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
        User user = getCurrentUser();
        if (user == null) {
            return new ArrayList<>();
        } else {
            return getCurrentUser().cart_oid;
        }
    }

    /**
     * 获取我的关注objectId列表
     *
     * @return
     */
    public List<String> getLoveOid() {
        User user = getCurrentUser();
        if (user == null) {
            return new ArrayList<>();
        } else {
            return getCurrentUser().love_oid;
        }
    }

    /**
     * 获取用户的唯一id
     *
     * @return
     */
    public String getUserOid() {
        User user = getCurrentUser();
        if (user == null) {
            return null;
        } else {
            return getCurrentUser().getObjectId();
        }
    }


}

