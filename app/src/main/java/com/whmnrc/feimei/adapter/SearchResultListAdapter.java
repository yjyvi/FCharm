package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.GetRecommendEnterpriseBean;
import com.whmnrc.feimei.beans.GetRecruitBean;
import com.whmnrc.feimei.beans.ProductListBean;
import com.whmnrc.feimei.ui.CommonH5WebView;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.ui.organization.OrganizationDetailsActivity;
import com.whmnrc.feimei.ui.organization.SearchBusinessMoreActivity;
import com.whmnrc.feimei.ui.product.ProductDetailsActivity;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.views.RatingBarView;
import com.whmnrc.mylibrary.utils.GlideUtils;

/**
 * @author yjyvi
 * @data 2018/5/18.
 */

public class SearchResultListAdapter extends CommonAdapter {

    private int positionType;
    private GetRecruitBean.ResultdataBean.RecruitBean mRecruitBean;
    private ProductListBean.ResultdataBean.EnterpriseBean mEnterpriseBean;
    private GetRecommendEnterpriseBean.ResultdataBean mOrgBean;

    public SearchResultListAdapter(Context context, int layoutId, int positionType) {
        super(context, layoutId);
        this.positionType = positionType;
    }


    @Override
    public void convert(ViewHolder holder, final Object goodsBean, int position) {
        switch (positionType) {
            case SearchActivity.SEARCH_ALL:
                break;
            case SearchActivity.SEARCH_PRODUCT:
                mEnterpriseBean= (ProductListBean.ResultdataBean.EnterpriseBean) goodsBean;
                holder.setText(R.id.tv_goods_name, mEnterpriseBean.getName());
                holder.setText(R.id.tv_rows, String.format("已浏览：%s人", mEnterpriseBean.getClickNumber()));
                if (mEnterpriseBean.getPrice() <= 0) {
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
                mOrgBean = (GetRecommendEnterpriseBean.ResultdataBean) goodsBean;
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

                holder.itemView.setOnClickListener(v -> OrganizationDetailsActivity.start(v.getContext(),mOrgBean.getID()));

                holder.setOnClickListener(R.id.tv_more, v -> SearchBusinessMoreActivity.start(v.getContext()));

                if (position == getDatas().size()-1) {
                    holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
                }else {
                    holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
                }

                break;
            case SearchActivity.SEARCH_RESOURCE:
            case SearchActivity.SEARCH_COLUMN:
                break;
            case SearchActivity.SEARCH_SPECIAL:
                mRecruitBean = (GetRecruitBean.ResultdataBean.RecruitBean) goodsBean;
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
                    CommonH5WebView.startCommonH5WebView(v.getContext(), mRecruitBean.getDescribe(), "职位详情");
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
