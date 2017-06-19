package com.handsome.didi.Base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handsome.didi.Bean.MessageEvent;
import com.handsome.didi.R;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    private SparseArray<View> mViews;
    private Intent intent;

    public abstract int getLayoutId();

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View v);

    public void onClick(View v) {
        if (v.getId() == R.id.iv_finish) {
            finish();
        }
        processClick(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        setContentView(getLayoutId());
        initViews();
        initListener();
        initData();
        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册EventBus
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onResume() {
        super.onResume();
        //友盟統計
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        //友盟統計
        MobclickAgent.onPause(this);
    }

    /**
     * 封装findViewById
     *
     * @param viewId
     * @param <E>
     * @return
     */
    public <E extends View> E findView(int viewId) {
        E view = (E) mViews.get(viewId);
        if (view == null) {
            view = (E) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
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
     * 弹出对话框
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast toast = new Toast(this);
        View view = View.inflate(this, R.layout.view_toast, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_toast);
        textView.setText(msg);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setMargin(0, 0);
        toast.show();
    }

    /**
     * 开启界面
     *
     * @param cls
     */
    public void startActivity(Class cls) {
        intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * 申请权限
     *
     * @param permissions
     */
    public void requestPermissions(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                }
                ActivityCompat.requestPermissions(this, new String[]{permission}, 0);
            }
        }
    }

    /**
     * EventBus处理事件
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(MessageEvent messageEvent) {
        //更新UI数据
        Log.e("11111", "classname:" + messageEvent.className);
        Log.e("22222", "classname:" + getClass().getName());
        if (messageEvent.className.contains(getClass().getName())) {
            initData();
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
