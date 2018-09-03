package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.RuleDescriptionBean;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class VipTypeAdapter extends CommonAdapter<RuleDescriptionBean> {
    public VipTypeAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, RuleDescriptionBean ruleDescriptionBean, int position) {
        holder.setText(R.id.tv_type_name, ruleDescriptionBean.getName());
        holder.setImageDrawable(R.id.iv_icon, ContextCompat.getDrawable(mContext,ruleDescriptionBean.getIcon()));

    }

}
