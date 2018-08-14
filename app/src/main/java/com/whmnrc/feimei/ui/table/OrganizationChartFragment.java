package com.whmnrc.feimei.ui.table;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
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
import com.whmnrc.feimei.adapter.OrganizationChartListAdapter;
import com.whmnrc.feimei.adapter.OrganizationChartTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.beans.GetEnterpriseTypeBean;
import com.whmnrc.feimei.beans.GetRecommendEnterpriseBean;
import com.whmnrc.feimei.presener.GetEnterpriseTypePresenter;
import com.whmnrc.feimei.presener.GetRecommendEnterprisePresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.MineActivity;
import com.whmnrc.feimei.ui.organization.SearchBusinessMoreActivity;
import com.whmnrc.feimei.utils.evntBusBean.BaseEvent;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @date 2018/1/30
 * 企业名录
 */

public class OrganizationChartFragment extends LazyLoadFragment implements OnRefreshLoadMoreListener, GetEnterpriseTypePresenter.GetEnterpriseTypeListener, GetRecommendEnterprisePresenter.GetRecommendEnterpriseListener {

    @BindView(R.id.rv_type)
    RecyclerView mRvType;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;

    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.header_layout)
    LinearLayout headerLayout;
    @BindView(R.id.banner)
    Banner mBanner;

    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.nsv_layout)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.iv_user_info)
    ImageView mIvUserInfo;


    private int page = 1;
    private int rows = 10;
    /**
     * 品牌的一页显示的最大数据
     */
    private int pageMax = 10;
    public GetEnterpriseTypePresenter mGetEnterpriseTypePresenter;
    public OrganizationChartTypeAdapter mOrganizationChartTypeAdapter;
    public GetRecommendEnterprisePresenter mGetRecommendEnterprisePresenter;
    public OrganizationChartListAdapter mOrganizationChartListAdapter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_organization_chart;
    }


    @Override
    protected void initViewData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mGetEnterpriseTypePresenter = new GetEnterpriseTypePresenter(this);
        mGetEnterpriseTypePresenter.getRecommendEnterpriseList();
        mGetRecommendEnterprisePresenter = new GetRecommendEnterprisePresenter(this);
        mGetRecommendEnterprisePresenter.getRecommendEnterpriseList(0);


        mRefresh.setOnRefreshLoadMoreListener(this);
        mRefresh.setEnableLoadMore(false);
        initOrgType();
        initOrgList();
    }

    private void initOrgList() {
        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        mOrganizationChartListAdapter = new OrganizationChartListAdapter(getActivity(), R.layout.item_organization_chart_list);
        mRvProductList.setAdapter(mOrganizationChartListAdapter);
    }

    private void initOrgType() {
        mRvType.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvType.setNestedScrollingEnabled(false);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.custom_divider));
        mRvType.addItemDecoration(divider);
        mOrganizationChartTypeAdapter = new OrganizationChartTypeAdapter(getActivity(), R.layout.item_organization_chart_type);
        mRvType.setAdapter(mOrganizationChartTypeAdapter);
        mOrganizationChartTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                SearchBusinessMoreActivity.start(view.getContext(),false,mOrganizationChartTypeAdapter.getDatas().get(position).getID());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static OrganizationChartFragment newInstance() {
        Bundle bundle = new Bundle();
        OrganizationChartFragment homeFragment = new OrganizationChartFragment();
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
        mGetEnterpriseTypePresenter.getRecommendEnterpriseList();
        mGetRecommendEnterprisePresenter.getRecommendEnterpriseList(0);
        refreshLayout.finishRefresh();
    }


    /**
     * @param goodsCommentEvent
     */
    @Subscribe
    public void changePrice(BaseEvent goodsCommentEvent) {

    }


    @OnClick({R.id.tv_search, R.id.iv_user_info, R.id.tv_more})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                SearchBusinessMoreActivity.start(v.getContext());
                break;
            case R.id.tv_more:
                SearchBusinessMoreActivity.start(v.getContext(), true, "");
                break;
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
    public void getEnterpriseTypeSuccess(List<GetEnterpriseTypeBean.ResultdataBean> beans) {
        mOrganizationChartTypeAdapter.setDataArray(beans);
        mOrganizationChartTypeAdapter.notifyDataSetChanged();
        isShowDialog(false);
    }

    @Override
    public void getEnterpriseTypeField() {
        isShowDialog(false);
    }

    @Override
    public void getRecommendEnterpriseSuccess(List<GetRecommendEnterpriseBean.ResultdataBean> beans) {
        mOrganizationChartListAdapter.setDataArray(beans);
        mOrganizationChartListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getRecommendEnterpriseField() {
        isShowDialog(false);
    }


}
