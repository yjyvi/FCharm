package com.whmnrc.feimei.ui.table;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BannerListBean;
import com.whmnrc.feimei.presener.GetBannerPresenter;
import com.whmnrc.feimei.ui.BaseFragment;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.industry.fragment.FragmentBookResource;
import com.whmnrc.feimei.ui.industry.fragment.FragmentFileResource;
import com.whmnrc.feimei.ui.industry.fragment.FragmentInformationResource;
import com.whmnrc.feimei.ui.industry.fragment.FragmentReadResource;
import com.whmnrc.feimei.ui.mine.MineActivity;
import com.whmnrc.mylibrary.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @date 2018/1/30
 * 行业资源
 */

public class IndustryResourcesFragment extends LazyLoadFragment implements GetBannerPresenter.GetBannerListener {

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

    private List<BaseFragment> mFragments = new ArrayList<>();

    String[] titles = new String[]{"阅读", "文库", "规格书", "光通资讯"};
    public GetBannerPresenter mGetBannerPresenter;


    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_industry_resources;
    }


    @Override
    protected void initViewData() {


        mGetBannerPresenter = new GetBannerPresenter(this);
        mGetBannerPresenter.getBanner(2);
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
                    mFragments.add(FragmentBookResource.newInstance(2));
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

    @Override
    public void getBannerSuccess(List<BannerListBean.ResultdataBean> bean) {
        initBanner(bean);
    }


    /**
     * 轮播图
     */
    private void initBanner(List<BannerListBean.ResultdataBean> bannerList) {
        if (mBanner != null) {
            mBanner.setDelayTime(3000);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.offsetLeftAndRight(10);
            mBanner.setImages(bannerList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    final BannerListBean.ResultdataBean  resultdataBeans = (BannerListBean.ResultdataBean) path;
                    String bannerUrl = resultdataBeans.getBanner_Url();
                    if (TextUtils.isEmpty(bannerUrl)) {
                        if (!bannerUrl.startsWith("http")) {
                            bannerUrl = getResources().getString(R.string.service_host_address) + bannerUrl;
                        }
                    }
                    GlideUtils.LoadImage(imageView.getContext(), bannerUrl, imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }).start();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }


    public class TableViewPagerAdapter extends FragmentPagerAdapter {

        public TableViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
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
        viewPager.setAdapter(new TableViewPagerAdapter(getFragmentManager()));
        viewPager.setOffscreenPageLimit(4);
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
                colorTransitionPagerTitleView.setOnClickListener(view -> viewPager.setCurrentItem(index));
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
}
