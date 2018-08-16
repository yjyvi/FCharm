package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ResourceFileListAdapter extends CommonAdapter {
    public ResourceFileListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object readBean, int position) {


//        holder.itemView.setOnClickListener(v -> PayActivity.start(v.getContext(), PayActivity.RESOURCE_PAY, "12"));
    }


}
