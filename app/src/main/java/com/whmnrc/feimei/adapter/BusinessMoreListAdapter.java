package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.EnterpriseListBean;
import com.whmnrc.feimei.pop.PopRatingRule;
import com.whmnrc.feimei.presener.GetRatingExplainPresenter;
import com.whmnrc.feimei.views.RatingBarView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class BusinessMoreListAdapter extends CommonAdapter<EnterpriseListBean.ResultdataBean.EnterpriseBean> {

    public PopRatingRule mPopRatingRule;

    public BusinessMoreListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, EnterpriseListBean.ResultdataBean.EnterpriseBean bean, int position) {
        holder.setText(R.id.tv_name, bean.getName());
        RatingBarView barView = holder.getView(R.id.rb_star);
        holder.setText(R.id.tv_desc, "主营：" + bean.getMainExplain());
        barView.setClickable(false);
        barView.setStar(bean.getRating(), false);
        holder.setText(R.id.tv_type, bean.getSubtitle());
        String phone = bean.getPhone();
        if (!TextUtils.isEmpty(phone)) {
            String resultTel = phone.substring(0, 7) + "****";
            holder.setText(R.id.tv_tel, resultTel);
        }
        holder.setText(R.id.tv_address, bean.getProvincial() + bean.getCity());

        holder.setOnClickListener(R.id.ll_rating_bar, v -> new GetRatingExplainPresenter(content -> {
            if (mPopRatingRule == null) {
                mPopRatingRule = new PopRatingRule(mContext, "评级规则", content);
            }
            mPopRatingRule.show();
        }).getRatingExplain());

        if (position == getDatas().size() - 1) {
            holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
        }
    }
}
