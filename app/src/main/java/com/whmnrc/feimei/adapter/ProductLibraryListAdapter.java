package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.ui.product.ProductDetailsActivity;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ProductLibraryListAdapter extends CommonAdapter {
    public ProductLibraryListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailsActivity.start(v.getContext());
                }
            });
    }


}
