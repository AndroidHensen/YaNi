package com.handsome.didi.Controller;

import com.handsome.didi.Bean.Store;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.CommonController;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class UserController extends CommonController {

    public interface OnQueryListener {
        void onQuery(List<User> list);
    }

    public interface OnLoginListener {
        void onLogin(boolean isLogin);
    }

    /**
     *
     * @param listener
     * @param U_ID
     */
    public void query(final OnQueryListener listener, long U_ID) {
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
     *
     * @param listener
     * @param name
     * @param password
     */
    public void login(final OnLoginListener listener, String name, String password) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("name", name);
        query.addWhereEqualTo("password", password);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                boolean isLogin;
                if (isLogin = list.isEmpty()) {
                    if (listener != null) {
                        listener.onLogin(isLogin);
                    }
                } else {
                    if (listener != null) {
                        listener.onLogin(isLogin);
                    }
                }
            }
        });
    }

}
