package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.pop.PopRatingRule;
import com.whmnrc.feimei.views.RatingBarView;

/**
 * @author yjyvi
 * @data 2018/5/18.
 */

public class HomeRecommendListAdapter extends CommonAdapter {

    private  int positionType;

    public HomeRecommendListAdapter(Context context, int layoutId, int positionType) {
        super(context, layoutId);
        this.positionType = positionType;
    }


    @Override
    public void convert(ViewHolder holder, final Object goodsBean, int position) {
        if (holder.getView(R.id.v_line) != null) {
            if (position == getItemCount()-1) {
                holder.getView(R.id.v_line).setVisibility(View.INVISIBLE);
            } else {
                holder.getView(R.id.v_line).setVisibility(View.VISIBLE);
            }
        }


        if (positionType==2) {
            holder.setOnClickListener(R.id.ll_start, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopRatingRule popRatingRule = new PopRatingRule(mContext,"评级规则","信用等级的评级规则整理中，信用等级的评级规则整理中，信用等级的评级规则整理中，信用等级的评级规则整理中，信用等级的评级规则整理中，信用等级的评级规则整理中，信用等级的评级规则整理中，信用等级的评级规则整理中，信用等级的评级规则整理中，信用等级的的评 ......");
                    popRatingRule.show();
                }
            });
            RatingBarView barView = holder.getView(R.id.rb_star);
            barView.setStarCount(position);
            barView.setClickable(false);

        }
    }


}
