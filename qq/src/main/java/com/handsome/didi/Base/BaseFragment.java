package com.handsome.didi.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isInitView = false;//是否建立起View
    private boolean isFirstLoad = true;//是否是第一次加载数据
    //findView相关
    public View convertView;
    private SparseArray<View> mViews;

    public abstract int getLayoutId();

    public abstract void initViews();

    public abstract void initData();

    public abstract void initListener();

    public abstract void processClick(View v);

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
        return convertView;
    }

    /**
     * 懒加载
     */
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


    @Override
    public void onClick(View v) {
        processClick(v);
    }

    /**
     * fragment中可以通过这个方法直接找到需要的view，而不需要进行类型强转
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
}
