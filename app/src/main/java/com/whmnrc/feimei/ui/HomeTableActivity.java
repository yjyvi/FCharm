package com.whmnrc.feimei.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.eventbus.HomeTableChangeEvent;
import com.whmnrc.feimei.ui.table.HomeFragment;
import com.whmnrc.feimei.ui.table.IndustryResourcesFragment;
import com.whmnrc.feimei.ui.table.OrganizationChartFragment;
import com.whmnrc.feimei.ui.table.ProductLibraryFragment;
import com.whmnrc.feimei.ui.table.SpecialInformationFragment;
import com.whmnrc.feimei.views.MyViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @date 2017/10/22
 * 主切换页面
 */
public class HomeTableActivity extends BaseActivity {


    private static final String CHANGE_HOME_TABLE = "change_home_table";
    @BindView(R.id.tv_table_home)
    TextView mTvTableHome;
    @BindView(R.id.tv_table_organization_chart)
    TextView mTvTableOrganizationChart;
    @BindView(R.id.tv_table_industry_resources)
    TextView mTvTableIndustryResources;
    @BindView(R.id.tv_table_product_library)
    TextView mTvTableProductLibrary;
    @BindView(R.id.tv_table_special_information)
    TextView mTvTableSpecialInformation;

    @BindView(R.id.tab_pager)
    MyViewPager mTabPager;

    private int mIndex;
    ArrayList<Fragment> mFragments = new ArrayList<>();

    public static void startHomeTableView(Context activity, int tab) {
        Intent intent = new Intent(activity, HomeTableActivity.class);
        intent.putExtra(CHANGE_HOME_TABLE, tab);
        activity.startActivity(intent);
    }

    @Override
    protected void back() {
        exit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        selectedView(mTvTableHome);
        mTabPager.setCurrentItem(0);
        Log.e("HomeTableActivity", "onNewIntent");
    }

    @Override
    protected void initViewData() {
        mIndex = getIntent().getIntExtra(CHANGE_HOME_TABLE, 0);
        EventBus.getDefault().register(this);

        mFragments.add(HomeFragment.newInstance());
        mFragments.add(OrganizationChartFragment.newInstance());
        mFragments.add(IndustryResourcesFragment.newInstance());
        mFragments.add(ProductLibraryFragment.newInstance());
        mFragments.add(SpecialInformationFragment.newInstance());


        UserManager.refresh();

        HelpsViewPagerAdapter helpsViewPagerAdapter = new HelpsViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mTabPager.setNoScroll(true);
        mTabPager.setAdapter(helpsViewPagerAdapter);
        mTabPager.setOffscreenPageLimit(2);
        selectedView(mTvTableHome);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_table_home;
    }

    public class HelpsViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> mFragments;

        public HelpsViewPagerAdapter(FragmentManager supportFragmentManager, ArrayList<Fragment> fragments) {
            super(supportFragmentManager);
            this.mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void tabChangeEvent(HomeTableChangeEvent homeTableChangeEvent) {
        switch (homeTableChangeEvent.getEventType()) {
            case HomeTableChangeEvent.TO_ORGANIZATION:
                mTabPager.setCurrentItem(1);
                selectedView(mTvTableOrganizationChart);
                break;
            case HomeTableChangeEvent.TO_INDUSTRY_RESOURCES:
                mTabPager.setCurrentItem(2);
                selectedView(mTvTableIndustryResources);
                break;
            case HomeTableChangeEvent.TO_PRODUCT_LIBRARY:
                mTabPager.setCurrentItem(3);
                selectedView(mTvTableProductLibrary);
                break;
            case HomeTableChangeEvent.TO_SPECIAL_INFORMATION:
                mTabPager.setCurrentItem(4);
                selectedView(mTvTableSpecialInformation);
                break;
            default:
                break;
        }


    }


    @Subscribe
    public void shoppingCartEvent(HomeTableChangeEvent homeTableChangeEvent) {

    }


    @OnClick({R.id.tv_table_home, R.id.tv_table_organization_chart, R.id.tv_table_industry_resources, R.id.tv_table_product_library, R.id.tv_table_special_information})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_table_home:
                mTabPager.setCurrentItem(0);
                selectedView(mTvTableHome);
                break;
            case R.id.tv_table_organization_chart:
                mTabPager.setCurrentItem(1);
                selectedView(mTvTableOrganizationChart);
                break;
            case R.id.tv_table_industry_resources:
                mTabPager.setCurrentItem(2);
                selectedView(mTvTableIndustryResources);
                break;
            case R.id.tv_table_product_library:
                mTabPager.setCurrentItem(3);
                selectedView(mTvTableProductLibrary);
                break;
            case R.id.tv_table_special_information:
                mTabPager.setCurrentItem(4);
                selectedView(mTvTableSpecialInformation);
                break;
            default:
                break;
        }
    }


    private View lastView;

    private void selectedView(View view) {
        if (lastView != null) {
            lastView.setSelected(false);
        }
        if (!view.isSelected()) {
            view.setSelected(true);
            lastView = view;
        } else {
            view.setSelected(false);
        }

    }
}
