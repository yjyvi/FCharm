package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.ReadListBean;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ColumnReadListAdapter extends CommonAdapter<ReadListBean.ResultdataBean.ReadBean> {
    public ColumnReadListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ReadListBean.ResultdataBean.ReadBean readBean, int position) {
//        holder.setText(R.id.tv_name, readBean.getName());
//        holder.setText(R.id.tv_title, readBean.getTitle());
//        holder.setText(R.id.tv_desc, readBean.getSubtitle());
//
//        TextView tvCollection = holder.getView(R.id.tv_collection);
////        tvCollection.setSelected(!tvCollection.isSelected());
//        holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(readBean.getCreateTime())));
//        GlideUtils.LoadImage(mContext, readBean.getImg(), holder.getView(R.id.iv_img));

    }


}
