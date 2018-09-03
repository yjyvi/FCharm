package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.VipTypeListBean;
import com.whmnrc.feimei.utils.GetViewHeightUtils;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class VipPriceAdapter extends CommonAdapter<VipTypeListBean.ResultdataBean> {
    public VipPriceAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    private VipPriceListener mVipPriceListener;

    public void setVipPriceListener(VipPriceListener vipPriceListener) {
        mVipPriceListener = vipPriceListener;
    }

    @Override
    public void convert(ViewHolder holder, VipTypeListBean.ResultdataBean bean, int position) {
        holder.setIsRecyclable(false);
        int getWidth = (holder.itemView.getContext().getResources().getDisplayMetrics().widthPixels - holder.itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.dm_40)) / 3;
        GetViewHeightUtils.changeViewHeight(holder.getView(R.id.rl_layout), getWidth);
        holder.setText(R.id.tv_money, String.valueOf(bean.getMoney()));
        holder.setText(R.id.tv_info, bean.getName());

        if (position == 0) {
            selectedView2(holder.itemView);
            mVipPriceListener.selectData(bean);
        }

        holder.itemView.setOnClickListener(v -> {
            if (mVipPriceListener != null) {
                selectedView2(v);
                mVipPriceListener.selectData(bean);
            }
        });
    }


    private View lastView2;

    private void selectedView2(View view) {
        if (view == null) {
            return;
        }
        if (lastView2 != null) {
            lastView2.setSelected(false);
        }
        if (!view.isSelected()) {
            view.setSelected(true);
            lastView2 = view;
        } else {
            view.setSelected(false);
        }

    }


    public interface VipPriceListener {
        void selectData(VipTypeListBean.ResultdataBean bean);
    }
}
