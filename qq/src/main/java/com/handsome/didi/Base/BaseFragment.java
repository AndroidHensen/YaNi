package com.handsome.didi.Base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handsome.didi.Bean.MessageEvent;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;

    private View convertView;
    private SparseArray<View> mViews;
    private Intent intent;

    public abstract int getLayoutId();

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View v);

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_finish) {
            getActivity().finish();
        }
        processClick(v);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViews = new SparseArray<>();
        convertView = inflater.inflate(getLayoutId(), container, false);
        initViews();

        isInitView = true;
        lazyLoad();

        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return convertView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //反注册EventBus
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void lazyLoad() {
        if (!isFirstLoad || !isVisible || !isInitView) {
            //不加载数据
            return;
        }
        //加载数据
        initListener();
        initData();

        isFirstLoad = false;
    }

    /**
     * 封装findViewById
     *
     * @param viewId
     * @param <E>
     * @return
     */
    public <E extends View> E findView(int viewId) {
        if (convertView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) convertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    /**
     * 封装setOnClickListener
     *
     * @param view
     * @param <E>
     */
    public <E extends View> void setOnClick(E view) {
        view.setOnClickListener(this);
    }

    /**
     * 开启界面
     *
     * @param cls
     */
    public void startActivity(Class cls) {
        intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    /**
     * 开启界面
     *
     * @param cls
     */
    public void startActivityForResult(Class cls, int requestCode) {
        intent = new Intent(getActivity(), cls);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 弹出对话框
     *
     * @param msg
     */
    public void showToast(String msg) {
        ToastUtils.showToast(getActivity(), msg);
    }

    /**
     * 申请权限
     *
     * @param permissions
     */
    public void requestPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(getActivity(), permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    list.add(permissions[i]);
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[i])) {
                        ToastUtils.showToast(getActivity(), "没有开启权限将会导致部分功能不可使用");
                    }
                }
            }
            ActivityCompat.requestPermissions(getActivity(), list.toArray(new String[permissions.length]), 0);
        }
    }

    /**
     * EventBus更新UI
     */
    public void onChangeDataInUI(String className) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.className = className;
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * EventBus处理事件
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        //更新UI数据
        Log.e("1111111111", "classname:" + messageEvent.className);
        Log.e("22222222222", "classname:" + getClass().getName());
        if (getClass().getName().equals(messageEvent.className)) {
            initData();
        }
    }

    /**
     * 设置标题栏标题
     *
     * @param title
     */
    public void setTitle(String title) {
        TextView tv_title = findView(R.id.tv_title);
        tv_title.setText(title);
    }

    /**
     * 设置标题栏有返回按钮
     */
    public void setTitleCanBack() {
        ImageView iv_finish = findView(R.id.iv_finish);
        iv_finish.setVisibility(View.VISIBLE);
        setOnClick(iv_finish);
    }
}
