package com.whmnrc.feimei.ui.table;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseFragment;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.industry.fragment.FragmentFileResource;
import com.whmnrc.feimei.ui.industry.fragment.FragmentInformationResource;
import com.whmnrc.feimei.ui.industry.fragment.FragmentReadResource;
import com.whmnrc.feimei.ui.mine.MineActivity;
import com.whmnrc.feimei.utils.evntBusBean.BaseEvent;
import com.youth.banner.Banner;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @date 2018/1/30
 * 行业资源
 */

public class IndustryResourcesFragment extends LazyLoadFragment implements OnRefreshLoadMoreListener {

    @BindView(R.id.header_layout)
    LinearLayout headerLayout;
    @BindView(R.id.tab)
    MagicIndicator tabLayout;
    @BindView(R.id.vp)
    ViewPager viewPager;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.banner)
    Banner mBanner;
//    @BindView(R.id.iv_user_info)
//    ImageView mIvUserInfo;

    private double mCurrencyPrice;

    private List<BaseFragment> mFragments = new ArrayList<>();
    private int page = 1;
    private int rows = 10;
    private String mCurrencyCode;
    /**
     * 品牌的一页显示的最大数据
     */
    private int pageMax = 10;

    String[] titles = new String[]{"阅读", "文库", "规格书", "光通资讯"};
    private int headerHeight;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_industry_resources;
    }


    @Override
    protected void initViewData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


//        mRefresh.setOnRefreshLoadMoreListener(this);

        initFragment();

        initTab();

    }


    private void initFragment() {
        for (int i = 0; i < titles.length; i++) {
            switch (i) {
                case 0:
                    mFragments.add(FragmentReadResource.newInstance(0));
                    break;
                case 1:
                    mFragments.add(FragmentFileResource.newInstance(1));
                    break;
                case 2:
                    mFragments.add(FragmentFileResource.newInstance(2));
                    break;
                case 3:
                    mFragments.add(FragmentInformationResource.newInstance(3));
                    break;
                default:
                    break;
            }
        }
    }



    @OnClick({R.id.iv_user_info})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_info:
                if (!UserManager.getIsLogin(v.getContext())) {
                    return;
                }
                MineActivity.start(v.getContext());
                break;
            default:
                break;
        }
    }

    public class TableViewPagerAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> mFragments;

        public TableViewPagerAdapter(FragmentManager supportFragmentManager, List<BaseFragment> fragments) {
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


    private void initTab() {
        viewPager.setAdapter(new TableViewPagerAdapter(getFragmentManager(), mFragments));
        viewPager.setOffscreenPageLimit(3);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(final Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                colorTransitionPagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.normal_blue_text_color));
                colorTransitionPagerTitleView.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.dm_14));
                colorTransitionPagerTitleView.setText(titles[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);

                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setLineHeight(4);
                indicator.setColors(ContextCompat.getColor(context, R.color.normal_blue_text_color));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });

        tabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabLayout, viewPager);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static IndustryResourcesFragment newInstance() {
        Bundle bundle = new Bundle();
        IndustryResourcesFragment homeFragment = new IndustryResourcesFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        page = 1;
        refreshLayout.finishRefresh();
    }


    /**
     * 修改货币显示
     *
     * @param goodsCommentEvent
     */
    @Subscribe
    public void changePrice(BaseEvent goodsCommentEvent) {

    }
}
