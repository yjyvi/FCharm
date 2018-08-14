package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.ui.industry.IndustryDetailsActivity;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class AuthorResourceListAdapter extends CommonAdapter {
    public AuthorResourceListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {
        holder.itemView.setOnClickListener(v -> IndustryDetailsActivity.start(mContext));
    }


}
