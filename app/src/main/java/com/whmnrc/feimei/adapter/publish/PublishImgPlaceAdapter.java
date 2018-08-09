package com.whmnrc.feimei.adapter.publish;

import com.luck.picture.lib.entity.LocalMedia;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ItemViewDelegate;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;

/**
 * Created by yong hao zeng on 2017/12/19.
 */

public class PublishImgPlaceAdapter implements ItemViewDelegate<LocalMedia> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_array_img;
    }

    @Override
    public boolean isForViewType(LocalMedia item, int position) {
        return item.getMimeType() == -2;
    }

    @Override
    public void convert(ViewHolder holder, LocalMedia localMedia, int position) {
                holder.setVisible(R.id.iv_cancel,false);
    }
}
