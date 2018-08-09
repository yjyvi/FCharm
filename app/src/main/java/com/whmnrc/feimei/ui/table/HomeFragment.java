package com.whmnrc.feimei.ui.table;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.HomePageListAdapter;
import com.whmnrc.feimei.eventbus.HomeTableChangeEvent;
import com.whmnrc.feimei.presener.HomePageDataPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.ui.mine.MineActivity;
import com.whmnrc.feimei.utils.TestDataUtils;
import com.whmnrc.feimei.views.LoadingDialog;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @date 2018/1/30
 * 首页
 */

public class HomeFragment extends LazyLoadFragment implements OnRefreshLoadMoreListener {

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


    private int page = 1;
    private int rows = 10;
    public HomePageDataPresenter mHomePageBannerPresenter;
    private LoadingDialog mLoadingDialog;
    /**
     * 品牌的一页显示的最大数据
     */
    private int pageMax = 10;
    public HomePageListAdapter mHomePageListAdapter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initViewData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        mRefresh.setOnRefreshLoadMoreListener(this);
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
        mHomePageListAdapter.setDataArray(TestDataUtils.initTestData(4));
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        page = 1;
        refreshLayout.finishRefresh();
    }


    @Subscribe
    public void changePrice(HomeTableChangeEvent homeTableChangeEvent) {

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
                SearchActivity.start(v.getContext());
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
}
