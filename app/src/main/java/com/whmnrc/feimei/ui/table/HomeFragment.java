package com.whmnrc.feimei.ui.table;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.MyApplication;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.HomePageListAdapter;
import com.whmnrc.feimei.beans.HomeDataBean;
import com.whmnrc.feimei.eventbus.HomeTableChangeEvent;
import com.whmnrc.feimei.presener.HomePageDataPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.ui.mine.MineActivity;
import com.whmnrc.feimei.utils.SPUtils;
import com.whmnrc.feimei.utils.VersionUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @date 2018/1/30
 * 首页
 */

public class HomeFragment extends LazyLoadFragment implements OnRefreshListener, HomePageDataPresenter.HomePageDataListener {

    @BindView(R.id.rv_recommend_list)
    RecyclerView mRvRecommendList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.header_layout)
    LinearLayout headerLayout;
    @BindView(R.id.banner)
    Banner mBanner;

    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.tv_search)
    TextView mTvSearch;

    @BindView(R.id.nsv_layout)
    NestedScrollView mNestedScrollView;

    @BindView(R.id.iv_activity_1)
    ImageView mIvActivity1;
    @BindView(R.id.iv_activity_4)
    ImageView mIvActivity4;
    @BindView(R.id.iv_activity_2)
    ImageView mIvActivity2;
    @BindView(R.id.iv_activity_3)
    ImageView mIvActivity3;


    public HomePageDataPresenter mHomePageBannerPresenter;
    public HomePageListAdapter mHomePageListAdapter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initViewData() {

        String data = SPUtils.getString(MyApplication.applicationContext, CommonConstant.Common.HOME_CACHE_DATA1);

        mHomePageBannerPresenter = new HomePageDataPresenter(this);
        mHomePageBannerPresenter.getHomeData();
        mRefresh.setOnRefreshListener(this);
        mRefresh.setEnableLoadMore(false);

        mRvRecommendList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRecommendList.setNestedScrollingEnabled(false);
        mRvRecommendList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelOffset(R.dimen.dm_8);
            }
        });
        mHomePageListAdapter = new HomePageListAdapter(getActivity(), R.layout.item_home_recommend_list_header);
        //显示缓存数据
        if (!TextUtils.isEmpty(data)) {
            List<HomeDataBean.ResultdataBean> resultdataBeans = JSON.parseArray(data, HomeDataBean.ResultdataBean.class);
            mHomePageListAdapter.setDataArray(resultdataBeans);
            initBanner(resultdataBeans.get(0).getBanners());
        }else {
            isShowDialog(true);
        }
        mRvRecommendList.setAdapter(mHomePageListAdapter);

    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();
        mHomePageBannerPresenter.getHomeData();
        VersionUtils.showDownloadPop(getActivity());
    }


    @OnClick({R.id.tv_search, R.id.iv_user_info, R.id.iv_activity_1, R.id.iv_activity_2, R.id.iv_activity_3, R.id.iv_activity_4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_activity_1:
                EventBus.getDefault().post(new HomeTableChangeEvent().setEventType(HomeTableChangeEvent.TO_ORGANIZATION));
                break;
            case R.id.iv_activity_2:
                EventBus.getDefault().post(new HomeTableChangeEvent().setEventType(HomeTableChangeEvent.TO_INDUSTRY_RESOURCES));
                break;
            case R.id.iv_activity_3:
                EventBus.getDefault().post(new HomeTableChangeEvent().setEventType(HomeTableChangeEvent.TO_PRODUCT_LIBRARY));
                break;
            case R.id.iv_activity_4:
                EventBus.getDefault().post(new HomeTableChangeEvent().setEventType(HomeTableChangeEvent.TO_SPECIAL_INFORMATION));
                break;
            case R.id.tv_search:
                SearchActivity.start(v.getContext(), SearchActivity.SEARCH_ALL, null);
                break;
            case R.id.iv_user_info:
                if (!UserManager.getIsLogin(getActivity())) {
                    return;
                }
                MineActivity.start(view.getContext());
                break;
            default:
                break;
        }
    }

    @Override
    public void loadHomeDataSuccess(HomeDataBean.ResultdataBean homeDataBean) {
        initBanner(homeDataBean.getBanners());

        ArrayList<HomeDataBean.ResultdataBean> data = new ArrayList<>();

        data.add(homeDataBean);
        data.add(homeDataBean);
        data.add(homeDataBean);
        data.add(homeDataBean);

        mHomePageListAdapter.setDataArray(data);
        mHomePageListAdapter.notifyDataSetChanged();

        List<HomeDataBean.ResultdataBean> datas = mHomePageListAdapter.getDatas();
        SPUtils.put(MyApplication.applicationContext, CommonConstant.Common.HOME_CACHE_DATA1, JSON.toJSONString(datas));
        mHomePageListAdapter.setDataLoadFinishListener(() -> isShowDialog(false));
    }


    /**
     * 轮播图
     */
    private void initBanner(List<HomeDataBean.ResultdataBean.BannersBean> bannerList) {
        if (mBanner != null) {
            mBanner.setDelayTime(3000);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.offsetLeftAndRight(10);
            mBanner.setImages(bannerList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    final HomeDataBean.ResultdataBean.BannersBean resultdataBeans = (HomeDataBean.ResultdataBean.BannersBean) path;
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
    public void loadHomeDataField() {
        isShowDialog(true);
    }
}
