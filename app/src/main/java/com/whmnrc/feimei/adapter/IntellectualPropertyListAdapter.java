package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.utils.TextSpannableUtils;

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
        holder.setText(R.id.tv_type, o.getName());

        TextView tvNumber = holder.getView(R.id.tv_number);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvEndTime = holder.getView(R.id.tv_end_time);

        String number = String.format("许可证编号：%s", o.getNumber());
        String time = String.format("发证日期：%s", o.getIssueTime());
        String endTime = String.format("有效期至：%s", o.getValidityTime());

        TextSpannableUtils.changeTextColor(tvNumber, number, 6, number.length(), ContextCompat.getColor(mContext, R.color.normal_gray));
        TextSpannableUtils.changeTextColor(tvTime, time, 5, time.length(), ContextCompat.getColor(mContext, R.color.normal_gray));
        TextSpannableUtils.changeTextColor(tvEndTime, endTime, 5, endTime.length(), ContextCompat.getColor(mContext, R.color.normal_gray));


    }

}
