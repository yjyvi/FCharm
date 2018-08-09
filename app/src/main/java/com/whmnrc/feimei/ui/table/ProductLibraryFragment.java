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
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ProductLibraryListAdapter;
import com.whmnrc.feimei.adapter.ProductLibraryTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.MineActivity;
import com.whmnrc.feimei.ui.product.ProductDetailsActivity;
import com.whmnrc.feimei.ui.product.SearchProductMoreActivity;
import com.whmnrc.feimei.utils.TestDataUtils;
import com.whmnrc.feimei.utils.evntBusBean.BaseEvent;
import com.whmnrc.feimei.views.LoadingDialog;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @date 2018/1/30
 * 行业产品库
 */

public class ProductLibraryFragment extends LazyLoadFragment implements OnRefreshLoadMoreListener {
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


    private int page = 1;
    private int rows = 10;
    private LoadingDialog mLoadingDialog;
    /**
     * 品牌的一页显示的最大数据
     */
    private int pageMax = 10;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_product_library;
    }


    @Override
    protected void initViewData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        mRefresh.setOnRefreshLoadMoreListener(this);

        initType();

        initProductList();

    }

    private void initProductList() {
        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        ProductLibraryListAdapter productLibraryListAdapter = new ProductLibraryListAdapter(getActivity(), R.layout.item_product_list);
        productLibraryListAdapter.setDataArray(TestDataUtils.initTestData(15));
        mRvProductList.setAdapter(productLibraryListAdapter);
        productLibraryListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ProductDetailsActivity.start(view.getContext());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void initType() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        mRvType.setLayoutManager(gridLayoutManager);
        mRvType.setNestedScrollingEnabled(false);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.custom_divider));
        mRvType.addItemDecoration(divider);
        ProductLibraryTypeAdapter productLibraryTypeAdapter = new ProductLibraryTypeAdapter(getActivity(), R.layout.item_organization_chart_type);
        productLibraryTypeAdapter.setDataArray(TestDataUtils.initTestData(12));
        mRvType.setAdapter(productLibraryTypeAdapter);
        productLibraryTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                SearchProductMoreActivity.start(view.getContext());
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
    public static ProductLibraryFragment newInstance() {
        Bundle bundle = new Bundle();
        ProductLibraryFragment homeFragment = new ProductLibraryFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    public void showEmpty() {
//        if (mAdapter != null && mAdapter.getDatas().size() == 0) {
//            EmptyListUtils.loadEmpty(true, mVsEmpty);
//        } else {
//            if (mVsEmpty != null) {
//                mVsEmpty.setVisibility(View.GONE);
//            }
//        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        mRefresh.finishLoadMore(1000);
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


    @OnClick({R.id.iv_back, R.id.tv_search, R.id.iv_user_info})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_search:
                SearchProductMoreActivity.start(view.getContext());
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
}
