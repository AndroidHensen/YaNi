package com.handsome.didi.Adapter.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class MainAdapter extends FragmentPagerAdapter {

    List<Fragment> list;
    List<String> title;

    public MainAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public MainAdapter(FragmentManager fm, List<Fragment> list,List<String> title) {
        super(fm);
        this.list = list;
        this.title = title;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(title == null){
            return super.getPageTitle(position);
        }else{
            return title.get(position);
        }
    }
}
