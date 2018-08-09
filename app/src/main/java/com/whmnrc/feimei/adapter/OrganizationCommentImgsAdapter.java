package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.utils.GetViewHeightUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;
import com.whmnrc.mylibrary.widget.photobigview.PhotoViewActivity;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class OrganizationCommentImgsAdapter extends CommonAdapter<String> {
    public OrganizationCommentImgsAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, final String o, final int position) {
        int getWidth = (mContext.getResources().getDisplayMetrics().widthPixels - mContext.getResources().getDimensionPixelOffset(R.dimen.dm_110)) / 3;
        GetViewHeightUtils.changeViewHeight(holder.getView(R.id.iv_img), getWidth);

        GlideUtils.LoadImage(mContext, o, (ImageView) holder.getView(R.id.iv_img));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoViewActivity.start(mContext, getDatas(), position);
            }
        });

    }


}
