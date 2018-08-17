package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.ResourcesFileBean;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.industry.IndustryDetailsActivity;
import com.whmnrc.feimei.ui.mine.PayActivity;
import com.whmnrc.feimei.ui.product.ProductSpecificationsActivity;
import com.whmnrc.feimei.utils.BookFileTypeUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ResourceFileListAdapter extends CommonAdapter<ResourcesFileBean.ResultdataBean.LibrarysBean> {
    public ResourceFileListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ResourcesFileBean.ResultdataBean.LibrarysBean readBean, int position) {

        if (getDatas().size() - 1 == position) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
        }

        holder.setText(R.id.tv_name, readBean.getName());
        holder.setText(R.id.tv_collection, readBean.getIsCollection() == 1 ? "已收藏" : "收藏");
        holder.getView(R.id.tv_collection).setSelected(readBean.getIsCollection() == 1);

        if (readBean.getType() == 4) {
            holder.setVisible(R.id.iv_video_img, true);
            GlideUtils.LoadImage(mContext, "", holder.getView(R.id.iv_video_img));
        } else {
            holder.setVisible(R.id.iv_video_img, false);
        }

        BookFileTypeUtils.showFileType(holder.getView(R.id.tv_name), readBean.getType());

        holder.setText(R.id.tv_desc, readBean.getSubtitle());
        holder.setText(R.id.tv_price, String.format("收费：%s", readBean.getPrice()));
        holder.setText(R.id.tv_download_count, String.format("%s次下载", readBean.getDownloadNumber()));
        holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(readBean.getCreateTime())));

        TextView tvDownload = holder.getView(R.id.tv_is_download);
        tvDownload.setOnClickListener(v -> {
            if (readBean.getIsPay() == 1 || UserManager.getUserIsVip()) {
                ProductSpecificationsActivity.showDownloadPop(mContext, tvDownload, readBean.getFilePath(), readBean.getName(), 1, readBean.getID(), new ProductSpecificationsActivity.DownloadListener() {
                    @Override
                    public void downloadSuccess() {
                        tvDownload.setEnabled(true);
                    }

                    @Override
                    public void downloadField() {
                        tvDownload.setEnabled(true);
                    }
                });
            } else {
                PayActivity.startFileResource(mContext, PayActivity.RESOURCE_PAY, readBean);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            IndustryDetailsActivity.startFielDetails(mContext, readBean, IndustryDetailsActivity.FILE_DETAILS_TYPE, position);
            IndustryDetailsActivity.setCollectionListener(isCollection -> {
                readBean.setIsCollection(isCollection ? 1 : 0);
                notifyItemChanged(position);
            });
        });


    }


}
