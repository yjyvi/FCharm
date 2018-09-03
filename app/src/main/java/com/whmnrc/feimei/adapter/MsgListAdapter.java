package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.MsgListBean;
import com.whmnrc.feimei.utils.TimeUtils;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class MsgListAdapter extends CommonAdapter<MsgListBean.ResultdataBean.PushBean> {
    public MsgListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, MsgListBean.ResultdataBean.PushBean pushBean, int position) {
        holder.setText(R.id.tv_title, pushBean.getTitle());
        holder.setText(R.id.tv_time, TimeUtils.getDateToString(pushBean.getCreateTime(), "yyyy/MM/dd HH:mm"));
        holder.setText(R.id.tv_content, pushBean.getSubTitle());
        holder.setVisible(R.id.v_point, pushBean.getIsRead() == 0);
    }

}
