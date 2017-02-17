package com.handsome.didi.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initViews(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initData();

    public abstract void initListener();

    public abstract void processClick(View v);

    @Override
    public void onClick(View v) {
        processClick(v);
    }
}
