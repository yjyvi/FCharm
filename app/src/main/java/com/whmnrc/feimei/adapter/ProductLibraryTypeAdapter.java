package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.ProductTypeBean;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ProductLibraryTypeAdapter extends CommonAdapter<ProductTypeBean.ResultdataBean> {
    public ProductLibraryTypeAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ProductTypeBean.ResultdataBean o, int position) {
        GlideUtils.LoadImage(mContext, o.getImg(), holder.getView(R.id.iv_img));
        holder.setText(R.id.tv_title, o.getName());
    }
}
