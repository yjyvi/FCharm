package com.whmnrc.feimei.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.VipPriceAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.VipTypeListBean;
import com.whmnrc.feimei.presener.GetVipTypePresenter;

import java.util.List;


/**
 * Created by guox on 2017/6/2 0002.
 * 赞赏弹窗
 */

public class PopAppreciate implements GetVipTypePresenter.GetVipTypeListener {

    private PopupWindow mPopupWindow;
    private Context mContext;

    private PopHintListener mPopHintListener;

    public TextView mTvPayPrice;
    public GetVipTypePresenter mGetVipTypePresenter;
    public Adapter mAdapter;
    public VipTypeListBean.ResultdataBean mSelectData;

    public void setPopHintListener(PopHintListener popHintListener) {
        mPopHintListener = popHintListener;
    }

    public PopAppreciate(Context context) {


        mGetVipTypePresenter = new GetVipTypePresenter(this);

        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_appreciate, null);

        mTvPayPrice = view.findViewById(R.id.tv_pay_price);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        ImageView ivClose = view.findViewById(R.id.tv_close);
        RecyclerView rvList = view.findViewById(R.id.rv_list);

        rvList.setLayoutManager(new GridLayoutManager(context, 4));
        mAdapter = new Adapter(context, R.layout.item_apprecalate_select);
        rvList.setAdapter(mAdapter);

        mAdapter.setVipPriceListener(bean -> {
            mTvPayPrice.setText(String.format("确定支付：%s元", bean.getMoney()));
            mSelectData = bean;
        });

        ivClose.setOnClickListener(v -> {
            if (mPopupWindow != null) {
                mPopupWindow.dismiss();
            }
        });

        tvConfirm.setOnClickListener(v -> {
            if (mPopupWindow != null) {
                mPopupWindow.dismiss();
            }

            if (mPopHintListener != null && mSelectData != null) {
                mPopHintListener.confirm(mSelectData);
            }
        });

        // 设置popwindow弹出大小
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.PopupWindow);
        // 设置PopupWindow以外部分的背景颜色  有一种变暗的效果
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        // 使其聚集
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);


    }

    public void show() {
        mGetVipTypePresenter.getVipTypeList(1);
    }


    private View lastView;

    private void selectedView(View view) {
        if (lastView != null) {
            lastView.setSelected(false);
        }
        if (!view.isSelected()) {
            view.setSelected(true);
            lastView = view;
        } else {
            view.setSelected(false);
        }

    }

    @Override
    public void getVipTypeSuccess(List<VipTypeListBean.ResultdataBean> resultdataBeans) {
        if (resultdataBeans == null) {
            return;
        }

        if (resultdataBeans.size() == 0) {
            return;
        }

        mAdapter.setDataArray(resultdataBeans);
        mAdapter.notifyDataSetChanged();

        PopUtils.setBackgroundAlpha((Activity) mContext, 0.5f);
        // 设置弹出位置
        mPopupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        // 刷新状态
        mPopupWindow.update();

        mPopupWindow.setOnDismissListener(() -> PopUtils.setBackgroundAlpha((Activity) mContext, 1f));

    }

    @Override
    public void getVipTypeField() {

    }

    public interface PopHintListener {
        void confirm(VipTypeListBean.ResultdataBean bean);
    }


    public class Adapter extends CommonAdapter<VipTypeListBean.ResultdataBean> {

        public Adapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, VipTypeListBean.ResultdataBean s, int position) {
            holder.setText(R.id.tv, String.format("%s元", s.getMoney()));

            if (position == 0) {
                if (mVipPriceListener != null) {
                    selectedView(holder.itemView);
                    mVipPriceListener.selectData(s);
                }
            }

            holder.itemView.setOnClickListener(v -> {
                if (mVipPriceListener != null) {
                    selectedView(v);
                    mVipPriceListener.selectData(s);
                }
            });

        }


        private VipPriceAdapter.VipPriceListener mVipPriceListener;

        public void setVipPriceListener(VipPriceAdapter.VipPriceListener vipPriceListener) {
            mVipPriceListener = vipPriceListener;
        }
    }

}
