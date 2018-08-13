package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.ProductListBean;
import com.whmnrc.feimei.ui.product.ProductDetailsActivity;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ProductLibraryListAdapter extends CommonAdapter<ProductListBean.ResultdataBean.EnterpriseBean> {
    public ProductLibraryListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ProductListBean.ResultdataBean.EnterpriseBean o, int position) {

        holder.setText(R.id.tv_goods_name, o.getName());
        holder.setText(R.id.tv_rows, String.format("已浏览：%s人", o.getClickNumber()));
        if (o.getPrice() <= 0) {
            holder.setVisible(R.id.tv_price, false);
            holder.setVisible(R.id.tv_sale, false);
        } else {
            holder.setVisible(R.id.tv_price, true);
            holder.setVisible(R.id.tv_sale, true);
            holder.setText(R.id.tv_sale, String.format("销量：%s件", o.getSales()));
            holder.setText(R.id.tv_price, String.format("￥%s", o.getPrice()));
        }
        GlideUtils.LoadImage(mContext, o.getImg(), holder.getView(R.id.iv_goods_img));
        holder.itemView.setOnClickListener(v -> ProductDetailsActivity.start(v.getContext(), o.getID()));
    }


}
