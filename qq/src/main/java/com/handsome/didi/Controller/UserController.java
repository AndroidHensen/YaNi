package com.handsome.didi.Controller;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.handsome.didi.Bean.Store;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.CommonController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.PrefUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class UserController extends CommonController {

    public UserController(Context context) {
        super(context);
    }

    public interface OnQueryListener {
        void onQuery(List<User> list);
    }

    public interface OnLoginListener {
        void onLogin(boolean isLogin);
    }

    public interface OnRegisterListener {
        void onRegister(boolean isRegister);
    }

    /**
     * 根据U_ID查询用户
     *
     * @param listener
     * @param U_ID
     */
    public void query(long U_ID, final OnQueryListener listener) {
        BmobQuery<User> query = new BmobQuery<>();
        query.setLimit(1);
        query.addWhereEqualTo("id", U_ID);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (listener != null) {
                    listener.onQuery(list);
                }
            }
        });
    }

    /**
     * 登陆
     *
     * @param listener
     * @param name
     * @param password
     */
    public void login(String name, String password, final OnLoginListener listener) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("name", name);
        query.addWhereEqualTo("password", password);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                boolean isLogin = !list.isEmpty();
                if (listener != null) {
                    listener.onLogin(isLogin);
                }
                if (isLogin) {
                    //登陆成功，自动保存用户信息
                    User user = list.get(0);
                    setUser(user);
                    setIsLogin(isLogin);
                }
            }
        });
    }

    /**
     * 注册
     *
     * @param name
     * @param password
     * @param listener
     */
    public void register(String name, String password, final OnRegisterListener listener) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    //注册成功
                    if (listener != null) {
                        listener.onRegister(true);
                    }
                } else {
                    //注册失败
                    if (listener != null) {
                        listener.onRegister(false);
                    }
                }
            }
        });
    }

    /**
     * 设置是否已经登陆
     *
     * @param isLogin
     */
    public void setIsLogin(boolean isLogin) {
        PrefUtils.putBoolean("isLogin", isLogin, mContext);
    }

    /**
     * 设置用户信息
     *
     * @param user
     */
    public void setUser(User user) {
        PrefUtils.putString("user_name", user.getName(), mContext);
        PrefUtils.putInt("user_rate", user.getRate(), mContext);
    }

    /**
     * 设置用户等级
     *
     * @param rate
     * @param resView
     */
    public void setUserRate(int rate, LinearLayout resView) {
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
     * 是否已经登陆
     *
     * @return
     */
    public boolean isLogin() {
        return PrefUtils.getBoolean("isLogin", false, mContext);
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public String getUserName() {
        return PrefUtils.getString("user_name", "您还未登陆哦~", mContext);
    }

    /**
     * 获取用户等级
     *
     * @return
     */
    public int getUserRate() {
        return PrefUtils.getInt("user_rate", -1, mContext);
    }
}

