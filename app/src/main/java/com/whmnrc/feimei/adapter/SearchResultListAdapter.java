package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.AllSearchBean;
import com.whmnrc.feimei.beans.GetRecommendEnterpriseBean;
import com.whmnrc.feimei.beans.GetRecruitBean;
import com.whmnrc.feimei.beans.NewsListBean;
import com.whmnrc.feimei.beans.ProductListBean;
import com.whmnrc.feimei.beans.ReadListBean;
import com.whmnrc.feimei.beans.RegulationBookListBean;
import com.whmnrc.feimei.beans.ResourcesFileBean;
import com.whmnrc.feimei.ui.CommonH5WebView;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.ui.industry.IndustryDetailsActivity;
import com.whmnrc.feimei.ui.organization.OrganizationDetailsActivity;
import com.whmnrc.feimei.ui.organization.SearchBusinessMoreActivity;
import com.whmnrc.feimei.ui.product.ProductDetailsActivity;
import com.whmnrc.feimei.ui.product.ProductSpecificationsActivity;
import com.whmnrc.feimei.utils.BookFileTypeUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.views.RatingBarView;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/5/18.
 */

public class SearchResultListAdapter extends CommonAdapter {

    private int positionType;

    public SearchResultListAdapter(Context context, int layoutId, int positionType) {
        super(context, layoutId);
        this.positionType = positionType;
    }


    @Override
    public void convert(ViewHolder holder, final Object goodsBean, int position) {
        switch (positionType) {
            case SearchActivity.SEARCH_ALL:
                AllSearchBean.ResultdataBean resultdataBean = (AllSearchBean.ResultdataBean) goodsBean;
                RecyclerView recyclerView = holder.getView(R.id.rv_product_list);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                SearchAllResultListAdapter searchResultListAdapter;
                switch (position) {
                    //产品
                    case 0:
                        searchResultListAdapter = new SearchAllResultListAdapter(mContext, R.layout.item_product_list, 1);
                        searchResultListAdapter.setDataArray(resultdataBean.getCommoditys());
                        break;
                    //企业
                    case 1:
                        searchResultListAdapter = new SearchAllResultListAdapter(mContext, R.layout.item_enterprise_list, 2);
                        searchResultListAdapter.setDataArray(resultdataBean.getEnterprises());
                        break;
                    //文库
                    case 2:
                        searchResultListAdapter = new SearchAllResultListAdapter(mContext, R.layout.item_library_resource_list, 3);
                        searchResultListAdapter.setDataArray(resultdataBean.getLibrarys());
                        break;
                    //阅读
                    case 3:
                        searchResultListAdapter = new SearchAllResultListAdapter(mContext, R.layout.item_resource_list, 4);
                        searchResultListAdapter.setDataArray(resultdataBean.getReads());
                        break;
                    //规格书
                    case 4:
                        searchResultListAdapter = new SearchAllResultListAdapter(mContext, R.layout.item_library_resource_list, 5);
                        searchResultListAdapter.setDataArray(resultdataBean.getRegulationBooks());
                        break;
                    //行业资讯
                    case 5:
                        searchResultListAdapter = new SearchAllResultListAdapter(mContext, R.layout.item_information_resource_list, 6);
                        searchResultListAdapter.setDataArray(resultdataBean.getNews());
                        break;
                    //招聘
                    case 6:
                        searchResultListAdapter = new SearchAllResultListAdapter(mContext, R.layout.item_recruitment_list, 7);
                        searchResultListAdapter.setDataArray(resultdataBean.getRecruits());
                        break;
                    default:
                        searchResultListAdapter = new SearchAllResultListAdapter(mContext, R.layout.item_recruitment_list, 7);
                        break;
                }
                recyclerView.setAdapter(searchResultListAdapter);

                break;
            case SearchActivity.SEARCH_PRODUCT:
                ProductListBean.ResultdataBean.EnterpriseBean mEnterpriseBean = (ProductListBean.ResultdataBean.EnterpriseBean) goodsBean;
                holder.setText(R.id.tv_goods_name, mEnterpriseBean.getName());
                holder.setText(R.id.tv_rows, String.format("已浏览：%s人", mEnterpriseBean.getClickNumber()));
                if (mEnterpriseBean.getPrice() <= 0.00d) {
                    holder.setVisible(R.id.tv_price, false);
                    holder.setVisible(R.id.tv_sale, false);
                } else {
                    holder.setVisible(R.id.tv_price, true);
                    holder.setVisible(R.id.tv_sale, true);
                    holder.setText(R.id.tv_sale, String.format("销量：%s件", mEnterpriseBean.getSales()));
                    holder.setText(R.id.tv_price, String.format("￥%s", mEnterpriseBean.getPrice()));
                }
                GlideUtils.LoadImage(mContext, mEnterpriseBean.getImg(), holder.getView(R.id.iv_goods_img));
                holder.itemView.setOnClickListener(v -> ProductDetailsActivity.start(v.getContext(), mEnterpriseBean.getID()));
                break;

            case SearchActivity.SEARCH_ORG:
                GetRecommendEnterpriseBean.ResultdataBean mOrgBean = (GetRecommendEnterpriseBean.ResultdataBean) goodsBean;
                holder.setText(R.id.tv_name, mOrgBean.getName());
                final RatingBarView barView = holder.getView(R.id.rb_star);
                barView.setClickable(false);
                barView.setStar(mOrgBean.getRating(), false);
                holder.setText(R.id.tv_label_type, mOrgBean.getLabel());
                holder.setText(R.id.tv_type, mOrgBean.getSubtitle());
                String phone = mOrgBean.getPhone();
                if (!TextUtils.isEmpty(phone)) {
                    String resultTel = phone.substring(0, 7) + "****";
                    holder.setText(R.id.tv_tel, resultTel);
                }
                holder.setText(R.id.tv_desc, "主营：" + mOrgBean.getMainExplain());
                holder.setText(R.id.tv_address, mOrgBean.getProvincial() + mOrgBean.getCity());

                holder.itemView.setOnClickListener(v -> OrganizationDetailsActivity.start(v.getContext(), mOrgBean.getID()));

                holder.setOnClickListener(R.id.tv_more, v -> SearchBusinessMoreActivity.start(v.getContext()));

                break;
            case SearchActivity.SEARCH_BOOK:
                RegulationBookListBean.ResultdataBean.ReadBean mBookBean = (RegulationBookListBean.ResultdataBean.ReadBean) goodsBean;
                holder.setText(R.id.tv_name, mBookBean.getName());
                holder.setText(R.id.tv_collection, mBookBean.getIsCollection() == 1 ? "已收藏" : "收藏");
                holder.getView(R.id.tv_collection).setSelected(mBookBean.getIsCollection() == 1);

                BookFileTypeUtils.showBookFileType(holder.getView(R.id.tv_name), mBookBean.getType());

                holder.getView(R.id.tv_price).setVisibility(View.INVISIBLE);
                holder.setVisible(R.id.rl_video, false);
                holder.setVisible(R.id.tv_download_count, false);

                holder.setText(R.id.tv_desc, mBookBean.getSubtitle());
                holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(mBookBean.getCreateTime())));

                TextView tvDownload = holder.getView(R.id.tv_is_download);
                tvDownload.setOnClickListener(v -> {
                    tvDownload.setEnabled(false);
                    tvDownload.post(() -> ProductSpecificationsActivity.showDownloadPop(mContext, tvDownload, mBookBean.getFilePath(), mBookBean.getName(), 0, mBookBean.getID(), new ProductSpecificationsActivity.DownloadListener() {
                        @Override
                        public void downloadSuccess() {
                            tvDownload.setEnabled(true);
                        }

                        @Override
                        public void downloadField() {
                            tvDownload.setEnabled(true);
                        }

                        @Override
                        public void downloading() {

                        }
                    }));
                });

                holder.itemView.setOnClickListener(v -> ProductSpecificationsActivity.start(mContext, mBookBean, 0));
                break;
            case SearchActivity.SEARCH_FILE:
                ResourcesFileBean.ResultdataBean.LibrarysBean mLibrarysBean = (ResourcesFileBean.ResultdataBean.LibrarysBean) goodsBean;
                holder.setText(R.id.tv_name, mLibrarysBean.getTitle());
                holder.setText(R.id.tv_collection, mLibrarysBean.getIsCollection() == 1 ? "已收藏" : "收藏");
                holder.getView(R.id.tv_collection).setSelected(mLibrarysBean.getIsCollection() == 1);

                if (mLibrarysBean.getType() == 4) {
                    holder.setVisible(R.id.rl_video, true);
                    GlideUtils.LoadImage(mContext, mLibrarysBean.getImg(), holder.getView(R.id.iv_video_img));
                } else {
                    holder.setVisible(R.id.rl_video, false);
                }

                BookFileTypeUtils.showFileType(holder.getView(R.id.tv_name), mLibrarysBean.getType());

                holder.setText(R.id.tv_desc, mLibrarysBean.getSubtitle());
                holder.setText(R.id.tv_price, String.format("收费：%s", mLibrarysBean.getPrice()));
                holder.setText(R.id.tv_download_count, String.format("%s次下载", mLibrarysBean.getDownloadNumber()));
                holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(mLibrarysBean.getCreateTime())));

                holder.itemView.setOnClickListener(v -> {
                    IndustryDetailsActivity.start(mContext, mLibrarysBean.getID(), IndustryDetailsActivity.FILE_DETAILS_TYPE, position);
                    IndustryDetailsActivity.setCollectionListener(isCollection -> {
                        mLibrarysBean.setIsCollection(isCollection ? 1 : 0);
                        notifyItemChanged(position);
                    });
                });

                break;
            case SearchActivity.SEARCH_INFORMATION:
                NewsListBean.ResultdataBean.NewsBean mNewsBean = (NewsListBean.ResultdataBean.NewsBean) goodsBean;
                holder.setText(R.id.tv_title, mNewsBean.getTitle());
                holder.setText(R.id.tv_download_count, String.format("%s已阅读", mNewsBean.getClickNumber()));
                holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(mNewsBean.getCreateTime())));
                GlideUtils.LoadImage(mContext, mNewsBean.getImg(), holder.getView(R.id.iv_img));

                holder.setText(R.id.tv_collection, mNewsBean.getIsCollection() == 1 ? "已收藏" : "收藏");
                holder.getView(R.id.tv_collection).setSelected(mNewsBean.getIsCollection() == 1);

                switch (mNewsBean.getType()) {
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

                holder.itemView.setOnClickListener((v) ->
                        CommonH5WebView.startCommonH5WebView(mContext, mNewsBean.getConten(), mNewsBean.getTitle())
                );

                if (position == getDatas().size() - 1) {
                    holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
                } else {
                    holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
                }
                break;

            case SearchActivity.SEARCH_READ:
                ReadListBean.ResultdataBean.ReadBean mReadBean = (ReadListBean.ResultdataBean.ReadBean) goodsBean;
                holder.setText(R.id.tv_name, mReadBean.getName());
                holder.setText(R.id.tv_title, mReadBean.getTitle());
                holder.setText(R.id.tv_desc, mReadBean.getSubtitle());
                holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(mReadBean.getCreateTime()), "yyyy-MM-dd HH:mm:ss"));
                GlideUtils.LoadImage(mContext, mReadBean.getImg(), holder.getView(R.id.iv_img));

                holder.itemView.setOnClickListener((v) -> {
                            IndustryDetailsActivity.start(mContext, mReadBean.getID(), IndustryDetailsActivity.READ_DETAILS_TYPE);
                        }
                );

                if (position == getDatas().size() - 1) {
                    holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
                } else {
                    holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
                }
                break;
            case SearchActivity.SEARCH_SPECIAL:
                GetRecruitBean.ResultdataBean.RecruitBean mRecruitBean = (GetRecruitBean.ResultdataBean.RecruitBean) goodsBean;
                holder.setText(R.id.tv_name, mRecruitBean.getName());
                if (TextUtils.isEmpty(mRecruitBean.getLabel())) {
                    holder.setVisible(R.id.tv_label, false);
                } else {
                    holder.setVisible(R.id.tv_label, true);
                    holder.setText(R.id.tv_label, mRecruitBean.getLabel());
                }
                holder.setText(R.id.tv_education, String.format("%s|%s", mRecruitBean.getQualificationsName(), mRecruitBean.getWorkYear()));
                holder.setText(R.id.tv_browse, String.valueOf(mRecruitBean.getClickNumber()));
                holder.setText(R.id.tv_price, mRecruitBean.getSalaryName());
                holder.setText(R.id.tv_address, String.format("%s-%s", mRecruitBean.getProvincial(), mRecruitBean.getCity()));
                holder.setText(R.id.tv_time, String.format("有效期：%s-%s", TimeUtils.getDateToString(mRecruitBean.getCreateTime(), "MM.dd"), TimeUtils.getDateToString(mRecruitBean.getValidityTime(), "MM.dd")));

                holder.itemView.setOnClickListener(v -> {
                    CommonH5WebView.startCommonH5WebView(v.getContext(), mRecruitBean.getDescribe(), mRecruitBean.getName(),true);
                });


                if (position == getDatas().size() - 1) {
                    holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
                } else {
                    holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }


}
