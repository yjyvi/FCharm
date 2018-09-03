package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.OrderListBean;
import com.whmnrc.feimei.utils.BookFileTypeUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/25.
 */

public class OrderProductAdapter extends CommonAdapter<OrderListBean.ResultdataBean.OrdersBean> {
    private boolean mIsProduct;

    public OrderProductAdapter(Context context, int layoutId, boolean isProduct) {
        super(context, layoutId);
        this.mIsProduct = isProduct;
    }

    @Override
    public void convert(ViewHolder holder, OrderListBean.ResultdataBean.OrdersBean ordersBean, int position) {
        String orderStateSt;
        switch (ordersBean.getState()) {
            case 0:
                orderStateSt = "待付款";
                break;
            case 1:
                orderStateSt = "已付款";
                break;
            case 2:
                orderStateSt = "已取消";
                break;
            case 3:
                orderStateSt = "申请退款";
                break;
            case 4:
                orderStateSt = "已退款";
                break;
            default:
                orderStateSt = "待付款";
                break;
        }

        holder.setText(R.id.tv_order_state, orderStateSt);
        holder.setText(R.id.tv_name, ordersBean.getName());

        if (mIsProduct) {
            GlideUtils.LoadImage(mContext, ordersBean.getImg(), holder.getView(R.id.iv_goods_img));
            holder.setText(R.id.tv_order_no, String.format("下单时间: %s", TimeUtils.getDateToString(Long.parseLong(ordersBean.getCreateTime()), "yyyy-MM-dd HH:mm:ss")));
            holder.setText(R.id.tv_source_price, String.format("￥%2.2f", ordersBean.getUnitPrice()));
            holder.setText(R.id.tv_count, String.format("X%s", ordersBean.getNumber()));
        } else {
            if (ordersBean.getOrderType() == 5) {
                BookFileTypeUtils.showFileType(holder.getView(R.id.tv_name), ordersBean.getType());
            }

            if (!TextUtils.isEmpty(ordersBean.getImg())) {
                holder.getView(R.id.iv_goods_img).setVisibility(View.VISIBLE);
                GlideUtils.LoadImage(mContext, ordersBean.getImg(), holder.getView(R.id.iv_goods_img));
            } else {
                holder.getView(R.id.iv_goods_img).setVisibility(View.GONE);
            }

            holder.setText(R.id.tv_time, String.format("%s", TimeUtils.getDateToString(Long.parseLong(ordersBean.getCreateTime()))));
            holder.setText(R.id.tv_desc, ordersBean.getSubTitle());
        }
    }


}
