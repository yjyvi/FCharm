package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.ReadListBean;
import com.whmnrc.feimei.ui.industry.IndustryDetailsActivity;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ResourceListAdapter extends CommonAdapter<ReadListBean.ResultdataBean.ReadBean> {
    public ResourceListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ReadListBean.ResultdataBean.ReadBean readBean, int position) {
        holder.setText(R.id.tv_name, readBean.getName());
        holder.setText(R.id.tv_title, readBean.getTitle());
        holder.setText(R.id.tv_desc, readBean.getSubtitle());
        holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(readBean.getCreateTime())));
        GlideUtils.LoadImage(mContext, readBean.getImg(), holder.getView(R.id.iv_img));

        holder.itemView.setOnClickListener((v) -> {
                    IndustryDetailsActivity.start(mContext, readBean.getID(), IndustryDetailsActivity.READ_DETAILS_TYPE);
                }
        );

        if (position == getDatas().size()-1) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        }else {
            holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
        }

    }


}
