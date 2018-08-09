package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class IntellectualPropertyListAdapter extends CommonAdapter<OrganizationDetailsBean.ResultdataBean.CertificateBean> {
    public IntellectualPropertyListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, OrganizationDetailsBean.ResultdataBean.CertificateBean o, int position) {
        holder.setText(R.id.tv_type,o.getName());
        holder.setText(R.id.tv_number,String.format("许可证编号：%s",o.getNumber()));
        holder.setText(R.id.tv_time,String.format("发证日期：%s",o.getIssueTime()));
        holder.setText(R.id.tv_end_time,String.format("有效期至：%s",o.getValidityTime()));
    }

}
