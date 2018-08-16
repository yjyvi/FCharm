package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.ColumnBean;
import com.whmnrc.feimei.ui.industry.ColumnActivity;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class AuthorResourceListAdapter extends CommonAdapter<ColumnBean.ResultdataBean> {
    public AuthorResourceListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ColumnBean.ResultdataBean bean, int position) {
        holder.setText(R.id.tv_name, bean.getName());
        GlideUtils.LoadImage(mContext, bean.getImg(), holder.getView(R.id.iv_user_img));

        holder.itemView.setOnClickListener((v) -> ColumnActivity.start(mContext, bean));
    }


}
