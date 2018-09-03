package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.OrderListBean;
import com.whmnrc.feimei.ui.mine.CommentActivity;
import com.whmnrc.feimei.ui.mine.OrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/7/25.
 */

public class OrderListAdapter extends CommonAdapter<OrderListBean.ResultdataBean.OrdersBean> {
    public OrderListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, OrderListBean.ResultdataBean.OrdersBean ordersBean, int position) {

        holder.setText(R.id.tv_total_price, String.format("合计:￥%2.2f", ordersBean.getMoney() ));

        if (ordersBean.getState() != 0) {
            holder.setVisible(R.id.tv_cancel, false);
        } else {
            holder.setVisible(R.id.tv_cancel, true);
        }

        RecyclerView mRvSaleList = holder.getView(R.id.rv_product_list);
        mRvSaleList.setNestedScrollingEnabled(false);

        mRvSaleList.setLayoutManager(new LinearLayoutManager(mContext));

        int itemProductList;
        boolean isProduct;
        if (ordersBean.getOrderType() == 0) {
            itemProductList = R.layout.item_order_product;
            isProduct = true;
        } else {
            isProduct = false;
            itemProductList = R.layout.item_order_resource;
        }

        List<OrderListBean.ResultdataBean.OrdersBean> ordersBeans = new ArrayList<>();
        ordersBeans.add(ordersBean);

        OrderProductAdapter orderProductAdapter = new OrderProductAdapter(mContext, itemProductList, isProduct);
        orderProductAdapter.setDataArray(ordersBeans);
        mRvSaleList.setAdapter(orderProductAdapter);
        orderProductAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                OrderDetailsActivity.start(view.getContext(), ordersBean.getID());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        holder.itemView.setOnClickListener(v -> OrderDetailsActivity.start(v.getContext(), ordersBean.getID()));


        holder.setOnClickListener(R.id.tv_cancel, v -> {
            if (mCancelOrPayOrderListener != null) {
                mCancelOrPayOrderListener.cancelOrder(ordersBean, position);
            }
        });


        if (ordersBean.getState() == 0) {
            holder.setVisible(R.id.tv_order_pay,true);
            holder.setText(R.id.tv_order_pay, "付款");
        } else if (ordersBean.getState() == 1) {
            holder.setVisible(R.id.tv_order_pay,true);
            holder.setText(R.id.tv_order_pay, "评价");
        }else {
            holder.setVisible(R.id.tv_order_pay,false);
        }

        holder.setOnClickListener(R.id.tv_order_pay, v -> {
            if (mCancelOrPayOrderListener != null) {
                if (ordersBean.getState() == 0) {
                    mCancelOrPayOrderListener.payOrder(ordersBean, position);
                } else if (ordersBean.getState() == 1) {
                    CommentActivity.start(mContext, ordersBean.getOtherID());
                }
            }
        });
    }

    private CancelOrPayOrderListener mCancelOrPayOrderListener;

    public void setCancelOrPayOrderListener(CancelOrPayOrderListener cancelOrPayOrderListener) {
        mCancelOrPayOrderListener = cancelOrPayOrderListener;
    }

    public interface CancelOrPayOrderListener {
        void cancelOrder(OrderListBean.ResultdataBean.OrdersBean ordersBean, int position);

        void payOrder(OrderListBean.ResultdataBean.OrdersBean ordersBean, int position);
    }
}
