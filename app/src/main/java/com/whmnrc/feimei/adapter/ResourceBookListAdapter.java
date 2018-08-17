package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.RegulationBookListBean;
import com.whmnrc.feimei.ui.product.ProductSpecificationsActivity;
import com.whmnrc.feimei.utils.BookFileTypeUtils;
import com.whmnrc.feimei.utils.TimeUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ResourceBookListAdapter extends CommonAdapter<RegulationBookListBean.ResultdataBean.ReadBean> {
    public ResourceBookListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    private BookCollectionListener mBookCollectionListener;

    public void setBookCollectionListener(BookCollectionListener bookCollectionListener) {
        mBookCollectionListener = bookCollectionListener;
    }

    @Override
    public void convert(ViewHolder holder, RegulationBookListBean.ResultdataBean.ReadBean readBean, int position) {
        holder.setText(R.id.tv_name, readBean.getName());
        holder.setText(R.id.tv_collection, readBean.getIsCollection() == 1 ? "已收藏" : "收藏");
        holder.getView(R.id.tv_collection).setSelected(readBean.getIsCollection() == 1);
        holder.getView(R.id.tv_collection).setOnClickListener(v -> {
            if (mBookCollectionListener != null) {
                mBookCollectionListener.clickCollection(position);
            }
        });

        BookFileTypeUtils.showBookFileType(holder.getView(R.id.tv_name), readBean.getType());

        holder.getView(R.id.tv_price).setVisibility(View.INVISIBLE);
        holder.setVisible(R.id.iv_video_img, false);
        holder.setVisible(R.id.tv_download_count, false);

        holder.setText(R.id.tv_desc, readBean.getSubtitle());
        holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(readBean.getCreateTime())));

        TextView tvDownload = holder.getView(R.id.tv_is_download);
        tvDownload.setOnClickListener(v -> {
            tvDownload.setEnabled(false);
            ProductSpecificationsActivity.showDownloadPop(mContext, tvDownload, readBean.getFilePath(), readBean.getName(), 0, readBean.getID(), new ProductSpecificationsActivity.DownloadListener() {
                @Override
                public void downloadSuccess() {
                    tvDownload.setEnabled(true);
                }

                @Override
                public void downloadField() {
                    tvDownload.setEnabled(true);
                }
            });
        });

        holder.itemView.setOnClickListener(v -> ProductSpecificationsActivity.start(mContext, readBean, 0));

    }

    public interface BookCollectionListener {
        void clickCollection(int position);
    }


}
