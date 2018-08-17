package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.RegulationBookListBean;
import com.whmnrc.feimei.utils.TimeUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ResourceBookListAdapter extends CommonAdapter<RegulationBookListBean.ResultdataBean.ReadBean> {
    public ResourceBookListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, RegulationBookListBean.ResultdataBean.ReadBean readBean, int position) {
        holder.setText(R.id.tv_name, readBean.getName());
        holder.setText(R.id.tv_collection, readBean.getIsCollection() == 1 ? "已收藏" : "收藏");
        holder.getView(R.id.tv_collection).setSelected(readBean.getIsCollection() == 1);

        holder.getView(R.id.tv_price).setVisibility(View.INVISIBLE);
        holder.setVisible(R.id.iv_video_img, false);
        holder.setVisible(R.id.tv_download_count, false);

        holder.setText(R.id.tv_desc, readBean.getSubtitle());
        holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(readBean.getCreateTime())));

        holder.getView(R.id.tv_is_download);

    }


}
