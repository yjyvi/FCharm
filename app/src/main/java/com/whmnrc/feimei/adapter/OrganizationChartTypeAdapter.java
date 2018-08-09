package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.GetEnterpriseTypeBean;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class OrganizationChartTypeAdapter extends CommonAdapter<GetEnterpriseTypeBean.ResultdataBean> {
    public OrganizationChartTypeAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, GetEnterpriseTypeBean.ResultdataBean bean, int position) {
        holder.setText(R.id.tv_title,bean.getName());
        GlideUtils.LoadImage(mContext,bean.getImg(), (ImageView) holder.getView(R.id.iv_img));
    }
}
