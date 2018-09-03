package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.HomeDataBean;
import com.whmnrc.feimei.pop.PopRatingRule;
import com.whmnrc.feimei.presener.GetRatingExplainPresenter;
import com.whmnrc.feimei.ui.CommonH5WebView;
import com.whmnrc.feimei.ui.industry.IndustryDetailsActivity;
import com.whmnrc.feimei.ui.organization.OrganizationDetailsActivity;
import com.whmnrc.feimei.ui.product.ProductDetailsActivity;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.views.RatingBarView;
import com.whmnrc.imp.GoToDetailsListener;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/5/18.
 */

public class HomeRecommendListAdapter extends CommonAdapter {

    private int positionType;
    private PopRatingRule mPopRatingRule;

    public HomeRecommendListAdapter(Context context, int layoutId, int positionType) {
        super(context, layoutId);
        this.positionType = positionType;
    }


    @Override
    public void convert(ViewHolder holder, final Object goodsBean, int position) {

        holder.setIsRecyclable(false);

        if (holder.getView(R.id.v_line) != null) {
            if (position == getItemCount() - 1) {
                holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
            } else {
                holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
            }
        }

        switch (positionType) {
            case 0:
                HomeDataBean.ResultdataBean.CommodityBean mCommodityBean = (HomeDataBean.ResultdataBean.CommodityBean) goodsBean;

                holder.setText(R.id.tv_goods_name, mCommodityBean.getName());
                holder.setText(R.id.tv_rows, String.format("已浏览：%s人", mCommodityBean.getClickNumber()));
                if (mCommodityBean.getPrice() <= 0.00d) {
                    holder.setVisible(R.id.tv_price, false);
                    holder.setVisible(R.id.tv_sale, false);
                } else {
                    holder.setVisible(R.id.tv_price, true);
                    holder.setVisible(R.id.tv_sale, true);
                    holder.setText(R.id.tv_sale, String.format("销量：%s件", mCommodityBean.getSales()));
                    holder.setText(R.id.tv_price, String.format("￥%2.2f", mCommodityBean.getPrice()));
                }
                GlideUtils.LoadImage(mContext, mCommodityBean.getImg(), holder.getView(R.id.iv_goods_img));
                holder.itemView.setOnClickListener(v -> {
                            ProductDetailsActivity.start(v.getContext(), mCommodityBean.getID());

                            holder.itemView.postDelayed(() -> {
                                if (mGoToDetailsListener != null) {
                                    mGoToDetailsListener.toDetails(position);
                                }
                            }, 500);

                        }
                );
                break;
            case 1:
                HomeDataBean.ResultdataBean.RecruitBean mRecruitBean = (HomeDataBean.ResultdataBean.RecruitBean) goodsBean;
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
                    CommonH5WebView.startCommonH5WebView(v.getContext(), mRecruitBean.getDescribe(), "职位详情",true);
                    if (mGoToDetailsListener != null) {
                        mGoToDetailsListener.toDetails(position);
                    }
                });

                break;
            case 2:
                HomeDataBean.ResultdataBean.EnterpriseBean mEnterpriseBean = (HomeDataBean.ResultdataBean.EnterpriseBean) goodsBean;
                holder.setText(R.id.tv_name, mEnterpriseBean.getName());
                final RatingBarView barView = holder.getView(R.id.rb_star);
                barView.setClickable(false);
                barView.setStar(mEnterpriseBean.getRating(), false);
                holder.setText(R.id.tv_type, mEnterpriseBean.getSubtitle());
                String phone = mEnterpriseBean.getPhone();
                if (!TextUtils.isEmpty(phone)) {
                    String resultTel = phone.substring(0, 7) + "****";
                    holder.setText(R.id.tv_tel, resultTel);
                }
                holder.setText(R.id.tv_desc, "主营：" + mEnterpriseBean.getMainExplain());
                holder.setText(R.id.tv_address, mEnterpriseBean.getProvincial() + mEnterpriseBean.getCity());

                holder.itemView.setOnClickListener(v -> OrganizationDetailsActivity.start(v.getContext(), mEnterpriseBean.getID()));

                holder.setOnClickListener(R.id.ll_rating_bar, v -> new GetRatingExplainPresenter(content -> {
                    if (mPopRatingRule == null) {
                        mPopRatingRule = new PopRatingRule(mContext, "评级规则", content);
                    }
                    mPopRatingRule.show();
                    mPopRatingRule.show();
                }).getRatingExplain());

                break;
            case 3:
                HomeDataBean.ResultdataBean.ReadBean mReadBean = (HomeDataBean.ResultdataBean.ReadBean) goodsBean;
                holder.setText(R.id.tv_name, mReadBean.getName());
                holder.setText(R.id.tv_title, mReadBean.getTitle());
                holder.setText(R.id.tv_desc, mReadBean.getSubtitle());
                holder.setText(R.id.tv_time, TimeUtils.getDateToString(Long.parseLong(mReadBean.getCreateTime()), "yyyy-MM-dd HH:mm:ss"));
                GlideUtils.LoadImage(mContext, mReadBean.getImg(), holder.getView(R.id.iv_img));

                holder.itemView.setOnClickListener((v) -> {
                            IndustryDetailsActivity.start(mContext, mReadBean.getID(), IndustryDetailsActivity.READ_DETAILS_TYPE);
                        }
                );

                break;
            default:
                break;
        }


    }

    private GoToDetailsListener mGoToDetailsListener;

    public void setGoToDetailsListener(GoToDetailsListener goToDetailsListener) {
        mGoToDetailsListener = goToDetailsListener;
    }


}
