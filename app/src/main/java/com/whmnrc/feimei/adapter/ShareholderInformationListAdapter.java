package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class ShareholderInformationListAdapter extends CommonAdapter<OrganizationDetailsBean.ResultdataBean.ShareholderBean> {
    public ShareholderInformationListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, OrganizationDetailsBean.ResultdataBean.ShareholderBean o, int position) {
        holder.setText(R.id.tv_name, o.getName());
        holder.setText(R.id.tv_money, o.getAmount());
        holder.setText(R.id.tv_time, o.getTime());
        holder.setText(R.id.tv_proportion, o.getRatio());

        createSignText(holder, o);

    }


    /**
     * 创建标签
     *
     * @param holder
     */
    private void createSignText(ViewHolder holder, OrganizationDetailsBean.ResultdataBean.ShareholderBean shareholderBean) {
        LinearLayout llSign = holder.getView(R.id.ll_sign);

        if (shareholderBean.getIsShareholder() == 1) {
            createView(llSign, "股东", R.color.IsShareholder, R.drawable.shape_line_green_bg);
        }

        if (shareholderBean.getIsBigShareholder() == 1) {
            createView(llSign, "大股东", R.color.good_price_red, R.drawable.shape_line_red_bg);
        }

        if (shareholderBean.getIsLegalPerson() == 1) {
            createView(llSign, "法人", R.color.good_price_red, R.drawable.shape_line_red_bg);
        }

        if (shareholderBean.getIsSurveillance() == 1) {
            createView(llSign, "监事", R.color.IsSurveillance, R.drawable.shape_line_yellow_bg);
        }


    }

    private void createView(LinearLayout llSign, String textContent, int color, int drawable) {
        TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(mContext.getResources().getDimensionPixelSize(R.dimen.dm_5), 0, 0, 0);
        textView.setLayoutParams(layoutParams);
        textView.setText(textContent);
        textView.setTextSize(mContext.getResources().getDimensionPixelSize(R.dimen.dm_4));
        textView.setTextColor(ContextCompat.getColor(mContext, color));
        textView.setPadding(
                mContext.getResources().getDimensionPixelSize(R.dimen.dm_2),
                mContext.getResources().getDimensionPixelSize(R.dimen.dm_3),
                mContext.getResources().getDimensionPixelSize(R.dimen.dm_2),
                        mContext.getResources().getDimensionPixelSize(R.dimen.dm_3));
        textView.setBackground(ContextCompat.getDrawable(mContext, drawable));
        llSign.addView(textView);
    }

}
