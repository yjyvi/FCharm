package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.ui.mine.OrderDetailsActivity;
import com.whmnrc.feimei.utils.TestDataUtils;

/**
 * @author yjyvi
 * @data 2018/7/25.
 */

public class OrderListAdapter extends CommonAdapter {
    public OrderListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {
        RecyclerView mRvSaleList = holder.getView(R.id.rv_product_list);
        mRvSaleList.setNestedScrollingEnabled(false);

        mRvSaleList.setLayoutManager(new LinearLayoutManager(mContext));

        int itemProductList;
        if (position %2== 0) {
            itemProductList = R.layout.item_order_product;
        } else {
            itemProductList = R.layout.item_order_resource;
        }


        OrderProductAdapter orderProductAdapter = new OrderProductAdapter(mContext, itemProductList);
        orderProductAdapter.setDataArray(TestDataUtils.initTestData(1));
        mRvSaleList.setAdapter(orderProductAdapter);
        orderProductAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                OrderDetailsActivity.start(view.getContext(),"");
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
}
