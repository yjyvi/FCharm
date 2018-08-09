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

public class OtherListAdapter extends CommonAdapter<OrganizationDetailsBean.ResultdataBean.RelationBean> {
    public OtherListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, OrganizationDetailsBean.ResultdataBean.RelationBean o, int position) {
        holder.setText(R.id.tv_org_name, o.getName());
    }

}
