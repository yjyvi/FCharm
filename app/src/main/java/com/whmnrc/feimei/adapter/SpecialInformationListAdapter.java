package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.GetRecruitBean;
import com.whmnrc.feimei.ui.CommonH5WebView;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.imp.GoToDetailsListener;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class SpecialInformationListAdapter extends CommonAdapter<GetRecruitBean.ResultdataBean.RecruitBean> {
    public SpecialInformationListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    private GoToDetailsListener mGoToDetailsListener;

    public void setGoToDetailsListener(GoToDetailsListener goToDetailsListener) {
        mGoToDetailsListener = goToDetailsListener;
    }

    @Override
    public void convert(ViewHolder holder, final GetRecruitBean.ResultdataBean.RecruitBean o, final int position) {
        holder.setText(R.id.tv_name, o.getName());
        if (TextUtils.isEmpty(o.getLabel())) {
            holder.setVisible(R.id.tv_label, false);
        } else {
            holder.setVisible(R.id.tv_label, true);
            holder.setText(R.id.tv_label, o.getLabel());
        }
        holder.setText(R.id.tv_education, String.format("%s|%s", o.getQualificationsName(), o.getWorkYear()));
        holder.setText(R.id.tv_browse, String.valueOf(o.getClickNumber()));
        holder.setText(R.id.tv_price, o.getSalaryName());
        holder.setText(R.id.tv_address, String.format("%s-%s", o.getProvincial(), o.getCity()));
        holder.setText(R.id.tv_time, String.format("有效期：%s-%s", TimeUtils.getDateToString(o.getCreateTime(), "MM.dd"), TimeUtils.getDateToString(o.getValidityTime(), "MM.dd")));

        holder.itemView.setOnClickListener(v -> {
            CommonH5WebView.startCommonH5WebView(v.getContext(), o.getDescribe(), "职位详情");
            if (mGoToDetailsListener != null) {
                mGoToDetailsListener.toDetails(position);
            }
        });



        if (position == getDatas().size() - 1) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
        }
    }


}
