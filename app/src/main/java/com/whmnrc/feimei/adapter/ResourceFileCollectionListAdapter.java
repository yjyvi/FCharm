package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.ProductCollectionListBean;
import com.whmnrc.feimei.ui.CommonH5WebView;
import com.whmnrc.feimei.ui.industry.IndustryDetailsActivity;
import com.whmnrc.feimei.utils.BookFileTypeUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ResourceFileCollectionListAdapter extends CommonAdapter<ProductCollectionListBean.ResultdataBean> {
    public ResourceFileCollectionListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ProductCollectionListBean.ResultdataBean readBean, int position) {

        holder.setIsRecyclable(false);

        if (readBean.isShowEdit()) {
            holder.setVisible(R.id.iv_select, true);
            holder.setOnClickListener(R.id.iv_select, v -> {
                if (!readBean.isSelect()) {
                    readBean.setSelect(true);
                    v.setSelected(true);
                } else {
                    readBean.setSelect(false);
                    v.setSelected(false);
                }
                if (mSelectDelCollectionListener != null) {
                    mSelectDelCollectionListener.select(readBean.isSelect());
                }

            });
        } else {
            holder.setVisible(R.id.iv_select, false);
        }

        if (getDatas().size() - 1 == position) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
        }

        String imgUrl;
        String contentUrl;

        switch (readBean.getType()) {
            case 1:
                contentUrl = "";
                imgUrl = readBean.getImg();
                holder.setText(R.id.tv_name, readBean.getTitle());
                holder.setText(R.id.tv_desc, readBean.getSubtitle());
                break;
            case 2:
                contentUrl = "";
                holder.setText(R.id.tv_name, readBean.getTitle());
                holder.setText(R.id.tv_desc, readBean.getSubtitle());
                imgUrl = readBean.getImg();
                BookFileTypeUtils.showFileType(holder.getView(R.id.tv_name), readBean.getIcoType());
                break;
            case 3:
                imgUrl = "";
                contentUrl = readBean.getTitle();
                BookFileTypeUtils.showBookFileType(holder.getView(R.id.tv_name), readBean.getIcoType());
                holder.setText(R.id.tv_name, readBean.getName());
                holder.setText(R.id.tv_desc, readBean.getSubtitle());
                break;
            case 4:
                contentUrl = readBean.getSubtitle();
                imgUrl = readBean.getImg();
                holder.setText(R.id.tv_name, readBean.getTitle());
                holder.setText(R.id.tv_desc, "");
                break;
            default:
                imgUrl = "";
                contentUrl = "";
                break;
        }


        if (!TextUtils.isEmpty(imgUrl)) {
            holder.setVisible(R.id.iv_img, true);
            GlideUtils.LoadImage(mContext, imgUrl, holder.getView(R.id.iv_img));
        } else {
            holder.setVisible(R.id.iv_img, false);
        }


        holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(readBean.getCreateTime()), "yyyy-MM-dd HH:mm:ss"));


        holder.itemView.setOnClickListener(v -> {
            if (readBean.getType() == 2) {
                IndustryDetailsActivity.start(mContext, readBean.getOtherID(), IndustryDetailsActivity.FILE_DETAILS_TYPE, position);
            } else if (readBean.getType() == 1) {
                IndustryDetailsActivity.start(mContext, readBean.getOtherID(), IndustryDetailsActivity.READ_DETAILS_TYPE, position);
            } else {
                String title = readBean.getTitle();
                if (readBean.getType() == 3) {
                    title = readBean.getName();
                }
                CommonH5WebView.startCommonH5WebView(mContext, contentUrl, title);
            }
        });


    }

    private ProductCollectionListAdapter.SelectDelCollectionListener mSelectDelCollectionListener;

    public void setGoToDetailsListener(ProductCollectionListAdapter.SelectDelCollectionListener selectDelCollectionListener) {
        mSelectDelCollectionListener = selectDelCollectionListener;
    }


}
