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
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by guox on 2017/6/2 0002.
 * 赞赏弹窗
 */

public class PopAppreciate {

    private PopupWindow mPopupWindow;
    private Context mContext;

    private PopHintListener mPopHintListener;

    private List<String> mDataList = new ArrayList<>();
    public TextView mTvPayPrice;


    public void setPopHintListener(PopHintListener popHintListener) {
        mPopHintListener = popHintListener;
    }

    public PopAppreciate(Context context) {

        mDataList.add("1元");
        mDataList.add("3元");
        mDataList.add("5元");
        mDataList.add("10元");
        mDataList.add("15元");
        mDataList.add("20元");
        mDataList.add("50元");
        mDataList.add("100元");


        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_appreciate, null);

        mTvPayPrice = view.findViewById(R.id.tv_pay_price);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        ImageView ivClose = view.findViewById(R.id.tv_close);
        RecyclerView rvList = view.findViewById(R.id.rv_list);

        rvList.setLayoutManager(new GridLayoutManager(context, 4));
        Adapter adapter = new Adapter(context, R.layout.item_apprecalate_select);
        adapter.setDataArray(mDataList);
        rvList.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mTvPayPrice.setText(String.format("确定支付：%s", mDataList.get(position)));
                selectedView(view);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }

                if (mPopHintListener != null) {
                    mPopHintListener.confirm();
                }
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
         PopUtils.setBackgroundAlpha((Activity) mContext, 0.5f);
        // 设置弹出位置
        mPopupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        // 刷新状态
        mPopupWindow.update();

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                 PopUtils.setBackgroundAlpha((Activity) mContext, 1f);
            }
        });
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

    public interface PopHintListener {
        void confirm();
    }


    class Adapter extends CommonAdapter<String> {

        public Adapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.tv, s);
        }
    }

}
