package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.ui.CommonH5WebView;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class OtherInfoAdapter extends CommonAdapter<OrganizationDetailsBean.ResultdataBean.EnterpriseOtherBean> {
    public OtherInfoAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, final OrganizationDetailsBean.ResultdataBean.EnterpriseOtherBean o, final int position) {
        holder.setText(R.id.tv_title, o.getTitle());
        holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(o.getTime()),"yyyy-MM-dd HH:mm:ss"));
        GlideUtils.LoadImage(mContext, o.getImg(), (ImageView) holder.getView(R.id.iv_img));
        holder.setText(R.id.tv_desc, o.getSubtitle());

        if (position == getDatas().size() - 1) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(v -> CommonH5WebView.startCommonH5WebView(v.getContext(), o.getConten(), o.getTitle()));
    }
}
