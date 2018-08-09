package com.whmnrc.feimei.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.OrderListAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.presener.OrderListPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.utils.TestDataUtils;
import com.whmnrc.feimei.utils.evntBusBean.OrderListEvent;
import com.whmnrc.feimei.views.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/5/21.
 */

public class OrderFragment extends LazyLoadFragment implements OrderListPresenter.OrderListListener, OnRefreshLoadMoreListener {
    @BindView(R.id.rv_order_list)
    RecyclerView mRvOrderList;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;

    private int page = 1;
    private LoadingDialog mLoadingDialog;
    public int mOrderType;
    private OrderListPresenter mOrderListPresenter;
    private boolean isPageMax;
    private OrderListAdapter mOrderListAdapter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_order;
    }

    @Override
    protected void initViewData() {

        EventBus.getDefault().register(this);
        mOrderType = getArguments().getInt("orderType");
        if (mOrderType == 1) {
            mLlFilter.setVisibility(View.VISIBLE);
        } else {
            mLlFilter.setVisibility(View.GONE);
        }
        mOrderListPresenter = new OrderListPresenter(this);


        mRvOrderList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOrderListAdapter = new OrderListAdapter(mRvOrderList.getContext(), R.layout.item_order_list);
        mRvOrderList.setFocusableInTouchMode(false);
        mRvOrderList.setNestedScrollingEnabled(false);
        mOrderListAdapter.setDataArray(TestDataUtils.initTestData(21));
        mRvOrderList.setAdapter(mOrderListAdapter);
        mRefresh.setOnRefreshLoadMoreListener(this);

        mOrderListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                OrderDetailsActivity.start(view.getContext());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    public static OrderFragment newInstance(int orderType) {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        args.putInt("orderType", orderType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void showLoadingDialog() {
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    @Subscribe
    public void orderListEvent(OrderListEvent orderListEvent) {
        page = 1;
        switch (orderListEvent.getEventType()) {
            case OrderListEvent.UNPAID:
                getOrder(1);
                break;
            case OrderListEvent.UNSHIPPED:
                getOrder(2);
                break;
            case OrderListEvent.RECEIPT:
                getOrder(3);
                break;
            case OrderListEvent.ALL:
                getOrder(0);
                break;
            default:
                break;
        }
    }

    private void getOrder(int type) {
        if (mOrderType == type) {
//            showLoadingDialog();
//            mOrderListPresenter.getOrderList(mOrderType, page);
        }
    }

    @Override
    public void getOrderListSuccess() {

//        if (page == 1) {
//            mOrderListAdapter.setDataArray(data);
//        } else {
//            List<OrderListBean.ResultdataBean> datas = mOrderListAdapter.getDatas();
//            datas.addAll(data);
//            mOrderListAdapter.setDataArray(datas);
//        }
//
//        if (data != null && data.size() == 0) {
//            isPageMax = true;
//        } else {
//            isPageMax = false;
//        }
//
//        mOrderListAdapter.notifyDataSetChanged();
//
//        mLoadingDialog.dismiss();
//        showEmpty();
    }

    @Override
    public void getOrderListField() {
        mLoadingDialog.dismiss();
    }


    public void showEmpty() {
//        if (mOrderListAdapter != null && mOrderListAdapter.getDatas().size() == 0) {
//            mRvGoodsList.setVisibility(View.VISIBLE);
//            mLlEmptyGoods.setVisibility(View.VISIBLE);
//            EmptyListUtils.loadEmpty(true, "No order", R.mipmap.no_order, mVsEmpty);
//        } else {
//            mRvGoodsList.setVisibility(View.GONE);
//            mLlEmptyGoods.setVisibility(View.GONE);
//            if (mVsEmpty != null) {
//                mVsEmpty.setVisibility(View.GONE);
//            }
//        }
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
        if (isPageMax) {
            refreshLayout.setNoMoreData(true);
            return;
        }
        page++;
//        mOrderListPresenter.getOrderList(mOrderType, page);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();
        page = 1;
        refreshLayout.setNoMoreData(false);
//        mOrderListPresenter.getOrderList(mOrderType, page);
    }

}
