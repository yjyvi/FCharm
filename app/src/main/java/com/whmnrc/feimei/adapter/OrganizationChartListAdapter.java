package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.GetRecommendEnterpriseBean;
import com.whmnrc.feimei.pop.PopRatingRule;
import com.whmnrc.feimei.presener.GetRatingExplainPresenter;
import com.whmnrc.feimei.ui.organization.OrganizationDetailsActivity;
import com.whmnrc.feimei.ui.organization.SearchBusinessMoreActivity;
import com.whmnrc.feimei.views.RatingBarView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class OrganizationChartListAdapter extends CommonAdapter<GetRecommendEnterpriseBean.ResultdataBean> {
    private PopRatingRule mPopRatingRule;

    public OrganizationChartListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, final GetRecommendEnterpriseBean.ResultdataBean bean, int position) {

        holder.setText(R.id.tv_name, bean.getName());
        final RatingBarView barView = holder.getView(R.id.rb_star);
        barView.setClickable(false);
        barView.setStar(bean.getRating(), false);
        holder.setText(R.id.tv_label_type, bean.getLabel());
        holder.setText(R.id.tv_type, bean.getSubtitle());
        String phone = bean.getPhone();
        if (!TextUtils.isEmpty(phone)) {
            String resultTel = phone.substring(0, 7) + "****";
            holder.setText(R.id.tv_tel, resultTel);
        }
        holder.setText(R.id.tv_desc, "主营：" + bean.getMainExplain());
        holder.setText(R.id.tv_address, bean.getProvincial() + bean.getCity());

        holder.itemView.setOnClickListener(v -> OrganizationDetailsActivity.start(v.getContext(), bean.getID()));

        holder.setOnClickListener(R.id.ll_rating_bar, v -> new GetRatingExplainPresenter(content -> {
            if (mPopRatingRule == null) {
                mPopRatingRule = new PopRatingRule(mContext, "评级规则", content);
            }
            mPopRatingRule.show();
        }).getRatingExplain());

        holder.setOnClickListener(R.id.tv_more, v -> SearchBusinessMoreActivity.start(v.getContext()));

        if (position == getDatas().size() - 1) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
        }
    }


}
