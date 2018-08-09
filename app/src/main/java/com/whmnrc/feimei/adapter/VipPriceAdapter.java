package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.utils.GetViewHeightUtils;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class VipPriceAdapter extends CommonAdapter {
    public VipPriceAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {
        int getWidth = (holder.itemView.getContext().getResources().getDisplayMetrics().widthPixels - holder.itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.dm_40)) / 3;
        GetViewHeightUtils.changeViewHeight(holder.getView(R.id.rl_layout), getWidth);
    }
}
