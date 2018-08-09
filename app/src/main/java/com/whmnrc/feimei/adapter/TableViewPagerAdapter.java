package com.whmnrc.feimei.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.whmnrc.feimei.ui.BaseFragment;
import com.whmnrc.feimei.ui.LazyLoadFragment;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/21.
 */

public class TableViewPagerAdapter extends FragmentPagerAdapter {

    private List<LazyLoadFragment> mFragments;

    public TableViewPagerAdapter(FragmentManager supportFragmentManager, List<LazyLoadFragment> fragments) {
        super(supportFragmentManager);
        this.mFragments = fragments;
    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
