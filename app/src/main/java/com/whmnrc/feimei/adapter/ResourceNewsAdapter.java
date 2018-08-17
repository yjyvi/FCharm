package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.NewsListBean;
import com.whmnrc.feimei.ui.CommonH5WebView;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.imp.GoToDetailsListener;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ResourceNewsAdapter extends CommonAdapter<NewsListBean.ResultdataBean.NewsBean> {
    public ResourceNewsAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }


    public interface NewsCollectionListener {
        void clickCollection(int position);
    }

    private NewsCollectionListener mNewsCollectionListener;

    public void setNewsCollectionListener(NewsCollectionListener newsCollectionListener) {
        mNewsCollectionListener = newsCollectionListener;
    }


    private GoToDetailsListener mGoToDetailsListener;

    public void setGoToDetailsListener(GoToDetailsListener goToDetailsListener) {
        mGoToDetailsListener = goToDetailsListener;
    }

    @Override
    public void convert(ViewHolder holder, NewsListBean.ResultdataBean.NewsBean readBean, int position) {
        holder.setText(R.id.tv_title, readBean.getTitle());
        holder.setText(R.id.tv_download_count, String.format("%s已阅读", readBean.getClickNumber()));
        holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(readBean.getCreateTime())));
        GlideUtils.LoadImage(mContext, readBean.getImg(), holder.getView(R.id.iv_img));

        holder.setText(R.id.tv_collection, readBean.getIsCollection() == 1 ? "已收藏" : "收藏");
        holder.getView(R.id.tv_collection).setSelected(readBean.getIsCollection() == 1);
        holder.getView(R.id.tv_collection).setOnClickListener(v -> {
            if (mNewsCollectionListener != null) {
                mNewsCollectionListener.clickCollection(position);
            }
        });

        switch (readBean.getType()) {
            case 0:
                holder.setText(R.id.tv_news_type, "标准规范");
                break;
            case 1:
                holder.setText(R.id.tv_news_type, "行业论文");
                break;
            case 2:
                holder.setText(R.id.tv_news_type, "技术文章");
                break;
            case 3:
                holder.setText(R.id.tv_news_type, "其它");
                break;
            default:
                break;
        }

        holder.itemView.setOnClickListener((v) -> {
                    CommonH5WebView.startCommonH5WebView(mContext, readBean.getConten(), readBean.getTitle());
                    holder.itemView.postDelayed(() -> {
                        if (mGoToDetailsListener != null) {
                            mGoToDetailsListener.toDetails(position);
                        }
                    }, 500);

                }
        );

        if (position == getDatas().size() - 1) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
        }

    }


}
