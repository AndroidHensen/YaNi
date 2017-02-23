package com.handsome.didi.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private boolean isPrepared;
    private boolean isVisible;

    public abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initData();

    public abstract void initListener();

    public abstract void processClick(View v);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initViews(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        //加载数据
        initData();
        initListener();
    }


    @Override
    public void onClick(View v) {
        processClick(v);
    }
}
