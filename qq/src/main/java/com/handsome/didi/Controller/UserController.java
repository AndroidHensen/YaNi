package com.handsome.didi.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.handsome.didi.Bean.User;
import com.handsome.didi.R;
import com.handsome.didi.Utils.SweetAlertUtils;
import com.handsome.didi.Utils.PrefUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
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

    /**
     * 根据U_ID查询用户
     *
     * @param listener
     * @param U_OID
     */
    public void query(String U_OID, final OnQueryListener listener) {
        BmobQuery<User> query = new BmobQuery<>();
        query.setLimit(1);
        query.addWhereEqualTo("objectId", U_OID);
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
                    Log.e("ss",e.getMessage());
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
        if (isLogin()) {
            return getCurrentUser().getCart_oid();
        } else {
            return null;
        }
    }

}

