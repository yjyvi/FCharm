package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.ProductCollectionListBean;
import com.whmnrc.feimei.ui.product.ProductDetailsActivity;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ProductCollectionListAdapter extends CommonAdapter<ProductCollectionListBean.ResultdataBean> {
    public ProductCollectionListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    private SelectDelCollectionListener mSelectDelCollectionListener;

    public void setGoToDetailsListener(SelectDelCollectionListener selectDelCollectionListener) {
        mSelectDelCollectionListener = selectDelCollectionListener;
    }

    public interface SelectDelCollectionListener {
        void select(boolean isSelect);
    }


    @Override
    public void convert(ViewHolder holder, ProductCollectionListBean.ResultdataBean o, int position) {
        holder.setIsRecyclable(false);
        if (o.isShowEdit()) {
            holder.setVisible(R.id.iv_select, true);
            holder.setOnClickListener(R.id.iv_select, v -> {
                if (!o.isSelect()) {
                    v.setSelected(true);
                    o.setSelect(true);
                } else {
                    v.setSelected(false);
                    o.setSelect(false);
                }
                if (mSelectDelCollectionListener != null) {
                    mSelectDelCollectionListener.select(o.isSelect());
                }
            });
        } else {
            holder.setVisible(R.id.iv_select, false);
        }

        holder.setText(R.id.tv_goods_name, o.getName());

        if (o.getPrice() <= 0.00d) {
            holder.getView(R.id.tv_price).setVisibility(View.INVISIBLE);
        } else {
            holder.setVisible(R.id.tv_price, true);
            holder.setText(R.id.tv_price, String.format("￥%2.2f", o.getPrice()));
        }
        holder.setText(R.id.tv_sale, String.format("销量：%s件", o.getSales()));

        GlideUtils.LoadImage(mContext, o.getImg(), holder.getView(R.id.iv_goods_img));
        holder.itemView.setOnClickListener(v -> {
                    ProductDetailsActivity.start(v.getContext(), o.getOtherID());
                }
        );

        if (getDatas().size() - 1 == position) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
        }
    }


}
