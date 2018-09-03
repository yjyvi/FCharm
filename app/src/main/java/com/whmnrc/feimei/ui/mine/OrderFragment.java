package com.whmnrc.feimei.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.OrderListAdapter;
import com.whmnrc.feimei.beans.OrderListBean;
import com.whmnrc.feimei.pop.PopOrderType;
import com.whmnrc.feimei.presener.OrderCancelOrRefundPresenter;
import com.whmnrc.feimei.presener.OrderListPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.EmptyListUtils;
import com.whmnrc.feimei.utils.evntBusBean.OrderListEvent;
import com.whmnrc.feimei.utils.evntBusBean.PayEvent;
import com.whmnrc.feimei.views.AlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/5/21.
 */

public class OrderFragment extends LazyLoadFragment implements OrderListPresenter.OrderListListener, OnRefreshLoadMoreListener, OrderCancelOrRefundPresenter.OrderOperationListener {
    @BindView(R.id.rv_order_list)
    RecyclerView mRvOrderList;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.tv_type_name)
    TextView mTvTypeName;

    public int mOrderType;
    private OrderListPresenter mOrderListPresenter;
    private OrderListAdapter mOrderListAdapter;
    public PopOrderType mPopOrderType;
    public int mOrderProductType = -1;
    public OrderCancelOrRefundPresenter mOrderCancelOrRefundPresenter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_order;
    }

    @Override
    protected void initViewData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mOrderType = getArguments().getInt("orderState");
        if (mOrderType == -1) {
            mLlFilter.setVisibility(View.VISIBLE);
        } else {
            mLlFilter.setVisibility(View.GONE);
        }

        mOrderListPresenter = new OrderListPresenter(this);

        mOrderCancelOrRefundPresenter = new OrderCancelOrRefundPresenter(this);


        mRvOrderList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOrderListAdapter = new OrderListAdapter(mRvOrderList.getContext(), R.layout.item_order_list);
        mRvOrderList.setFocusableInTouchMode(false);
        mRvOrderList.setNestedScrollingEnabled(false);
        mRvOrderList.setAdapter(mOrderListAdapter);
        mRefresh.setOnRefreshLoadMoreListener(this);

        getOrder(mOrderType);

        mOrderListAdapter.setCancelOrPayOrderListener(new OrderListAdapter.CancelOrPayOrderListener() {
            @Override
            public void cancelOrder(OrderListBean.ResultdataBean.OrdersBean ordersBean, int position) {

                new AlertDialog(getActivity()).builder().setTitle("提示!")
                        .setMsg("确认要取消订单吗?")
                        .setCancelable(true)
                        .setPositiveButton("确定", view -> {
                            isShowDialog(true);
                            mOrderCancelOrRefundPresenter.cancelOrder(ordersBean.getID());
                        })
                        .setNegativeButton("取消", view -> {

                        }).show();

            }

            @Override
            public void payOrder(OrderListBean.ResultdataBean.OrdersBean ordersBean, int position) {
                PayActivity.startOrderPay(getActivity(), PayActivity.ONE_PAY, String.valueOf(ordersBean.getMoney()), ordersBean.getID());
            }
        });
    }


    public static OrderFragment newInstance(int orderState) {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        args.putInt("orderState", orderState);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Subscribe
    public void orderListEvent(OrderListEvent orderListEvent) {
        switch (orderListEvent.getEventType()) {
            case OrderListEvent.ORDER_NO_PAY:
                getOrder(0);
                break;
            case OrderListEvent.ORDER_PAY:
                getOrder(1);
                break;
            case OrderListEvent.ALL:
                getOrder(-1);
                break;
            default:
                break;
        }
    }

    private void getOrder(int type) {
        if (mOrderType == type) {
            isShowDialog(true);
            mOrderListPresenter.getOrderList(true, mOrderType, mOrderProductType);
        }
    }

    @Override
    public void getOrderListSuccess(OrderListBean.ResultdataBean orderListBean, boolean isRefresh) {

        if (isRefresh) {
            if (mOrderListAdapter!=null && mOrderListAdapter.getDatas()!=null && mOrderListAdapter.getDatas().size()>0) {
                mOrderListAdapter.getDatas().clear();
            }
            mOrderListAdapter.setDataArray(orderListBean.getOrders());
        } else {
            List<OrderListBean.ResultdataBean.OrdersBean> datas = mOrderListAdapter.getDatas();

            if (orderListBean.getPagination().getRecords() == datas.size()) {
                mRefresh.setEnableLoadMore(false);
            }

            datas.addAll(orderListBean.getOrders());
            mOrderListAdapter.setDataArray(datas);
        }


        mOrderListAdapter.notifyDataSetChanged();

        showEmpty();

        isShowDialog(false);
    }

    @Override
    public void getOrderListField() {

    }


    public void showEmpty() {
        if (mOrderListAdapter != null && mOrderListAdapter.getDatas().size() == 0) {
            EmptyListUtils.loadEmpty(true, "暂无订单！", R.mipmap.no_order, mVsEmpty);
        } else {
            if (mVsEmpty != null) {
                mVsEmpty.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
        mOrderListPresenter.getOrderList(false, mOrderType, mOrderProductType);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();
        mRefresh.setEnableLoadMore(true);
        mOrderListPresenter.getOrderList(true, mOrderType, mOrderProductType);
    }


    @OnClick(R.id.ll_filter)
    public void onClick() {
        if (mPopOrderType == null) {
            mPopOrderType = new PopOrderType(getActivity(), mLlFilter);
        }

        mPopOrderType.show();
        mPopOrderType.setPopHintListener(bean -> {
                    mTvTypeName.setText(bean.getTypeName());
                    mOrderProductType = bean.getType();
                    mOrderListPresenter.getOrderList(true, mOrderType, bean.getType());
                }
        );
    }

    @Override
    public void cancelSuccess() {
        isShowDialog(false);
        mOrderListPresenter.getOrderList(true, mOrderType, mOrderProductType);
        UserManager.refresh();
    }

    @Override
    public void refundSuccess() {

    }

    @Override
    public void cancelField() {

    }

    @Override
    public void refundField() {

    }


    @Subscribe
    public void payEvent(PayEvent payEvent) {
        if (payEvent.getEventType() == PayEvent.PAY_SUCCESS) {
            mOrderListPresenter.getOrderList(true, mOrderType, mOrderProductType);
        }
    }
}
