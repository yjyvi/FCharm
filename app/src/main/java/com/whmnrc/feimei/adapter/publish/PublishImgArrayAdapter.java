package com.whmnrc.feimei.adapter.publish;

import android.view.View;
import android.widget.ImageView;

import com.luck.picture.lib.entity.LocalMedia;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ItemViewDelegate;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * Created by yong hao zeng on 2017/12/19.
 */

public class PublishImgArrayAdapter implements ItemViewDelegate<LocalMedia> {
    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public PublishImgArrayAdapter(OnClick onClick) {
        this.onClick = onClick;
    }


    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_array_img2;
    }

    @Override
    public boolean isForViewType(LocalMedia item, int position) {
        return item.getMimeType() != -2;
    }

    @Override
    public void convert(final ViewHolder holder, LocalMedia localMedia, int position) {

        GlideUtils.LoadImage(holder.itemView.getContext(),localMedia.getPath(),(ImageView) holder.getView(R.id.iv_img));

        holder.setVisible(R.id.iv_cancel, true);
        holder.setTag(R.id.iv_cancel, position);
        holder.setOnClickListener(R.id.iv_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                if (onClick != null) {
                    onClick.onCancelClick(view, position);
                }
            }
        });
    }

    public interface OnClick {
        void onCancelClick(View view, int position);
    }
}
